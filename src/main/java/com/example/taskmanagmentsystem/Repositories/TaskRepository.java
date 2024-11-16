package com.example.taskmanagmentsystem.Repositories;

import com.example.taskmanagmentsystem.entities.Task;
import com.example.taskmanagmentsystem.utils.GeneralRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TaskRepository extends GeneralRepository<Task> {

}
