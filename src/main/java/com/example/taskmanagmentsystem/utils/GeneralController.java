package com.example.taskmanagmentsystem.utils;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class GeneralController<T extends GeneralEntity<T>> {
    private final GeneralService<T> service;

    public GeneralController(GeneralRepository<T> repository) {
        this.service = new GeneralService<>(repository){};
    }

    @GetMapping("")
    public List<T> getAllEntities(){
        return service.getAllEntities();
    }
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional

    public T create(@RequestBody T entity){
        return service.createEntity(entity);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteEntity(id);
    }

    @GetMapping("/{id}")
    public Optional<T> getById(@PathVariable Long id){
        return service.findByID(id);
    }
    @PutMapping("/{id}")
    public T update(@PathVariable Long id,@RequestBody T entity){
         return service.update(id,entity);
    }

}
