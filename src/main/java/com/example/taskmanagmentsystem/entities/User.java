package com.example.taskmanagmentsystem.entities;

import com.example.taskmanagmentsystem.utils.GeneralEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User implements GeneralEntity<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "login", nullable = false, length = 100)
    private String login;

    @Nationalized
    @Column(name = "password", nullable = false)
    private String password;

    @Nationalized
    @Column(name = "email", nullable = false)
    private String email;

    @Nationalized
    @Column(name = "role", length = 100)
    private String role;

    @OneToMany(mappedBy = "idUser")
    @JsonManagedReference
    private Set<Project> projects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Task> tasks = new LinkedHashSet<>();

   @Override
    public void update(User source) {
       setEmail(source.getEmail());
       setPassword(source.getPassword());
       setRole(source.getRole());
       setProjects(source.getProjects());
       setLogin(source.getLogin());
       setTasks(source.getTasks());
    }

    public Long getId() {
        return (long)id;
    }

    @Override
    public User createNewInstance() {
        return null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}