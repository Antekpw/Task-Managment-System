package com.example.taskmanagmentsystem.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "LOGIN_NAME", nullable = false, length = 100)
    private String loginName;

    @Nationalized
    @Column(name = "PASSWORD_NAME", nullable = false)
    private String passwordName;

    @Nationalized
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Nationalized
    @Column(name = "ROLE_NAME", length = 100)
    private String roleName;

    @OneToMany(mappedBy = "idUser")
    private Set<Project> projects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUser")
    private Set<Task> tasks = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswordName() {
        return passwordName;
    }

    public void setPasswordName(String passwordName) {
        this.passwordName = passwordName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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