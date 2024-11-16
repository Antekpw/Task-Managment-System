package com.example.taskmanagmentsystem.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface GeneralRepository<T extends GeneralEntity<T>> extends JpaRepository<T,Long> {
}
