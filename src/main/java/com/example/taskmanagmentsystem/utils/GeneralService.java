package com.example.taskmanagmentsystem.utils;

import jakarta.persistence.EntityNotFoundException;


import java.util.List;
import java.util.Optional;


public class GeneralService<T extends GeneralEntity<T>> {
    private final GeneralRepository<T> repository;


    public GeneralService( GeneralRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> getAllEntities() {
        return repository.findAll();
    }

    public T createEntity(T entity) {
        return repository.save(entity);
    }

    public void deleteEntity(Long id) {
        repository.deleteById(id);
    }


    public Optional<T> findByID(Long id){
        System.out.println("szukam po id");
        return repository.findById(id);
    }
    public T update(Long id,T updated){
        repository.findById(id)
                .map(exisitingEnitity -> {
                    exisitingEnitity.update(updated);
                    return repository.save(exisitingEnitity);
                }).orElseThrow(()-> new EntityNotFoundException("Entity with id: " +  id+ " not found"));
        return null;
    }
}

