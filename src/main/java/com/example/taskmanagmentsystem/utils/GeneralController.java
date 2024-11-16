package com.example.taskmanagmentsystem.utils;

import com.example.taskmanagmentsystem.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GeneralController<T extends GeneralEntity<T>> {
    private final GeneralService<T> service;

    public GeneralController(GeneralRepository<T> repository) {
        this.service = new GeneralService<T>(repository){};
    }

    @GetMapping("")
    public List<T> getAllEntities(T entity){
        return service.getAllEntities();
    }
    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T created){
        return ResponseEntity.ok(service.create(created));
    }
//    public T create(@RequestBody T entity){
//        return service.createEntity(entity);
//    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteEntity(id);
    }
}
