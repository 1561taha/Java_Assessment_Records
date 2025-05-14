package com.Record.CRUD.Service;


import com.Record.CRUD.Model.RecordDTO;
import com.Record.CRUD.Model.User;
import com.Record.CRUD.Repo.CategoryRepository;
import com.Record.CRUD.Repo.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Record.CRUD.Model.Record;

@Service
public class RecordService {
    @Autowired
    private RecordRepository repo;
    @Autowired
    private CategoryRepository catRepo;

    public com.Record.CRUD.Model.Record save(Record rec) {
        return repo.save(rec); }
    public Optional<Record> findById(Long id) { return repo.findById(id); }
    public void deleteById(Long id) { repo.deleteById(id); }
    public void deleteAllByIds(List<Long> ids) { repo.deleteAllById(ids); }
    public List<RecordDTO> findAll(User user, Boolean active, String search) {
        List<Record> list;
        if (active != null && search != null && !search.isEmpty())
            list = repo.findByUserAndActiveAndNameContainingIgnoreCase(user, active, search);
        else if (active != null)
            list = repo.findByUserAndActive(user, active);
        else if (search != null && !search.isEmpty())
            list = repo.findByUserAndNameContainingIgnoreCase(user, search);
        else
            list = repo.findByUser(user);
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
    public RecordDTO toDTO(Record r) {
        RecordDTO dto = new RecordDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setDescription(r.getDescription());
        dto.setCategoryId(r.getCategory().getId());
        dto.setCategoryName(r.getCategory().getName());
        dto.setActive(r.getActive());
        return dto;
    }
}
