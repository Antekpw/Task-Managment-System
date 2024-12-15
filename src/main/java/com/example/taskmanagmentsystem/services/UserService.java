package com.example.taskmanagmentsystem.services;

import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.utils.GeneralRepository;
import com.example.taskmanagmentsystem.utils.GeneralService;
import org.springframework.stereotype.Service;



@Service
public class UserService extends GeneralService<User> {


    public UserService(GeneralRepository<User> repository) {
        super(repository);
    }
}

