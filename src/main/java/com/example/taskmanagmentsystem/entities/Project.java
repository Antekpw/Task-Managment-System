package com.example.taskmanagmentsystem.entities;

import com.example.taskmanagmentsystem.utils.GeneralEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "projects",schema ="dbo")
public class Project implements GeneralEntity<Project> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

    @Nationalized
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "creation_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant creationDate;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "id_user", nullable = true)
//    @JsonBackReference
//    private User idUser;
@ManyToMany(mappedBy = "projects")
private Set<User> sharedUsers = new HashSet<>();
    @OneToMany(mappedBy = "idProject",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Task> tasks = new LinkedHashSet<>();

    @Override
    public void update(Project source) {

    }

    public Long getId() {
        return id;
    }

    @Override
    public Project createNewInstance() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

//    public User getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(User idUser) {
//        this.idUser = idUser;
//    }

    public Set<Task> getTasks() {
        return tasks;
    }
    public Set<User> getSharedUsers() {
        return sharedUsers;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void setSharedUsers(Set<User> sharedUsers) {
        this.sharedUsers = sharedUsers;
    }
}