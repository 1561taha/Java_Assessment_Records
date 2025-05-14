package com.Record.CRUD.Controller;

import com.Record.CRUD.Model.Category;
import com.Record.CRUD.Model.RecordDTO;
import com.Record.CRUD.Model.User;
import com.Record.CRUD.Repo.CategoryRepository;
import com.Record.CRUD.Repo.UserRepository;
import com.Record.CRUD.Model.Record;
import com.Record.CRUD.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/records")
@CrossOrigin(origins = "http://localhost:3000")
public class RecordController {
    @Autowired
    private RecordService service;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CategoryRepository catRepo;

    private User getUser(UserDetails ud) {
        return userRepo.findByEmail(ud.getUsername()).orElseThrow();
    }

    @PostMapping
    public Map<String, Object> create(@AuthenticationPrincipal UserDetails ud, @RequestBody RecordDTO dto) {
        User user = getUser(ud);
        Category cat = catRepo.findById(dto.getCategoryId()).orElseThrow();
        Record rec = new Record(null, dto.getName(), dto.getDescription(), cat, dto.getActive(), user);
        service.save(rec);
        return Map.of("success", true);
    }

    @GetMapping
    public List<RecordDTO> list(@AuthenticationPrincipal UserDetails ud,
                                @RequestParam(required = false) Boolean active,
                                @RequestParam(required = false) String search) {
        User user = getUser(ud);
        return service.findAll(user, active, search);
    }

    @GetMapping("/{id}")
    public RecordDTO get(@AuthenticationPrincipal UserDetails ud, @PathVariable Long id) {
        User user = getUser(ud);
        Record rec = service.findById(id).orElseThrow();
        if (!rec.getUser().getId().equals(user.getId())) throw new RuntimeException("Not found");
        return service.toDTO(rec);
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@AuthenticationPrincipal UserDetails ud, @PathVariable Long id, @RequestBody RecordDTO dto) {
        User user = getUser(ud);
        Record rec = service.findById(id).orElseThrow();
        if (!rec.getUser().getId().equals(user.getId())) throw new RuntimeException("Not found");
        Category cat = catRepo.findById(dto.getCategoryId()).orElseThrow();
        rec.setName(dto.getName());
        rec.setDescription(dto.getDescription());
        rec.setCategory(cat);
        rec.setActive(dto.getActive());
        service.save(rec);
        return Map.of("success", true);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@AuthenticationPrincipal UserDetails ud, @PathVariable Long id) {
        User user = getUser(ud);
        Record rec = service.findById(id).orElseThrow();
        if (!rec.getUser().getId().equals(user.getId())) throw new RuntimeException("Not found");
        service.deleteById(id);
        return Map.of("success", true);
    }

    @PostMapping("/bulk-delete")
    public Map<String, Object> bulkDelete(@AuthenticationPrincipal UserDetails ud, @RequestBody Map<String, List<Long>> body) {
        User user = getUser(ud);
        List<Long> ids = body.get("ids");
        List<Record> recs = ids.stream().map(service::findById).filter(Optional::isPresent).map(Optional::get)
                .filter(r -> r.getUser().getId().equals(user.getId())).toList();
        service.deleteAllByIds(recs.stream().map(Record::getId).toList());
        return Map.of("success", true);
    }
}
