/*package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.Repositories.UserRepository;
import com.example.taskmanagmentsystem.entities.User;

import com.example.taskmanagmentsystem.utils.GeneralController;
import com.example.taskmanagmentsystem.utils.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController extends GeneralController<User> {
    @Autowired
    public UserController(UserRepository userRepository){
        super(userRepository);
    }
    public ResponseEntity<User> create(@RequestBody User user){
        return userRepository
    }
}*/
package com.example.taskmanagmentsystem.controllers;

import com.example.taskmanagmentsystem.Repositories.TaskRepository;
import com.example.taskmanagmentsystem.Repositories.UserRepository;
import com.example.taskmanagmentsystem.entities.Task;
import com.example.taskmanagmentsystem.entities.User;
import com.example.taskmanagmentsystem.services.UserService;
import com.example.taskmanagmentsystem.utils.GeneralController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends GeneralController<User> {
   /* private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }*/
   public UserController(UserRepository userRepository) {
       super(userRepository);
   }
}

