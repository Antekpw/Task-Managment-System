package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.Repositories.UserRepository;
import com.example.taskmanagmentsystem.entities.User;

import com.example.taskmanagmentsystem.utils.GeneralController;
import com.example.taskmanagmentsystem.utils.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController extends GeneralController<User> {
    public UserController(UserRepository userRepository){
        super(userRepository);
    }
}
