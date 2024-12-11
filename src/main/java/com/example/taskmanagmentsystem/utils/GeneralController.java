package com.example.taskmanagmentsystem.utils;

import com.example.taskmanagmentsystem.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
   /* public ResponseEntity<T> create(@RequestBody T created){
        return ResponseEntity.created(service.create(created));
    }*/
    public T create(@RequestBody T entity){
        return service.createEntity(entity);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteEntity(id);
    }
}
