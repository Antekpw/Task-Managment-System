package com.example.taskmanagmentsystem.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Entity
@Table(name = "TASK")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TASK", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "TASK_NAME", nullable = false)
    private String taskName;

    @Nationalized
    @Column(name = "DESCRIPTION_BODY", nullable = false)
    private String descriptionBody;

    @Column(name = "CREATION_DATE", nullable = false)
    private Instant creationDate;

    @Nationalized
    @Column(name = "TASK_STATUS", nullable = false)
    private String taskStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USER", nullable = false)
    private User idUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PROJECT", nullable = false)
    private Project idProject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }

}