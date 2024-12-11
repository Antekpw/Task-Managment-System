package com.example.taskmanagmentsystem.Repositories;

import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.utils.GeneralRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GeneralRepository<User> {
}
