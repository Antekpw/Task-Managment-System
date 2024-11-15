package com.example.taskmanagmentsystem.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PROJECT")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROJECT", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "PROJECT_NAME", nullable = false)
    private String projectName;

    @Nationalized
    @Column(name = "DESCRIPTION_BODY", nullable = false)
    private String descriptionBody;

    @Column(name = "CREATION_DATE", nullable = false)
    private Instant creationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USER", nullable = false)
    private User idUser;

    @OneToMany(mappedBy = "idProject")
    private Set<Task> tasks = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescriptionBody() {
        return descriptionBody;
    }

    public void setDescriptionBody(String descriptionBody) {
        this.descriptionBody = descriptionBody;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}