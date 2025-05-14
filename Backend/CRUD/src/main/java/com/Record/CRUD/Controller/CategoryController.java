package com.Record.CRUD.Controller;

import com.Record.CRUD.Model.Category;
import com.Record.CRUD.Repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryRepository repo;

    @GetMapping
    public List<Category> all() { return repo.findAll(); }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return repo.save(category);
    }
}
