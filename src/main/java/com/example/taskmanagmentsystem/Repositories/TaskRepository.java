package com.example.taskmanagmentsystem.Repositories;

import com.example.taskmanagmentsystem.entities.Task;

import com.example.taskmanagmentsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByIdUser(User user);
    // Custom query methods can be defined here if needed
    // For example:
    // List<Task> findByName(String name);
    // List<Task> findByStatus(String status)
    long countByIdUserAndStatus(User user, String status);
}
