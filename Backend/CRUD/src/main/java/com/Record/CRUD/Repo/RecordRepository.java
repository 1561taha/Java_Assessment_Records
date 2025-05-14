package com.Record.CRUD.Repo;

import com.Record.CRUD.Model.User;
import com.Record.CRUD.Model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByUser(User user);
    List<Record> findByUserAndActive(User user, Boolean active);
    List<Record> findByUserAndNameContainingIgnoreCase(User user, String name);
    List<Record> findByUserAndActiveAndNameContainingIgnoreCase(User user, Boolean active, String name);
}