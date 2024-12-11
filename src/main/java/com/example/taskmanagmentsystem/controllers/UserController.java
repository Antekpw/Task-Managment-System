package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.Repositories.TaskRepository;
import com.example.taskmanagmentsystem.Repositories.UserRepository;
import com.example.taskmanagmentsystem.entities.Task;
import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.utils.GeneralController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends GeneralController<User> {

   public UserController(UserRepository userRepository) {
       super(userRepository);
   }
}

