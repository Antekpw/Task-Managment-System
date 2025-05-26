package com.example.taskmanagmentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TaskManagmentSystemApplication {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("kurde"));
        SpringApplication.run(TaskManagmentSystemApplication.class, args);
    }

}
