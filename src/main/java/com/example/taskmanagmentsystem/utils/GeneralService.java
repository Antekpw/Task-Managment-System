package com.example.taskmanagmentsystem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Transactional
    public T create(T newDomain){
        T dbDomain = newDomain.createNewInstance();
        return repository.save(dbDomain);
    }
}

