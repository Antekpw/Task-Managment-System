package com.example.taskmanagmentsystem.dto;



public class DashboardStatsDto {
    private long todoTasks;
    private long inProgressTasks;
    private long doneTasks;
    private long overdueTasks;

    // Konstruktor
    public DashboardStatsDto(long todoTasks, long inProgressTasks,
                             long doneTasks) {
        this.todoTasks = todoTasks;
        this.inProgressTasks = inProgressTasks;
        this.doneTasks = doneTasks;

    }

    // Gettery
    public long getTodoTasks() {
        return todoTasks;
    }

    public long getInProgressTasks() {
        return inProgressTasks;
    }

    public long getDoneTasks() {
        return doneTasks;
    }

    public long getOverdueTasks() {
        return overdueTasks;
    }

    // Settery (jeśli potrzebne)
    public void setTodoTasks(long todoTasks) {
        this.todoTasks = todoTasks;
    }

    public void setInProgressTasks(long inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
    }

    public void setDoneTasks(long doneTasks) {
        this.doneTasks = doneTasks;
    }

    public void setOverdueTasks(long overdueTasks) {
        this.overdueTasks = overdueTasks;
    }

    // Metoda toString() (opcjonalnie)
    @Override
    public String toString() {
        return "DashboardStatsDto{" +
                "todoTasks=" + todoTasks +
                ", inProgressTasks=" + inProgressTasks +
                ", doneTasks=" + doneTasks +
                ", overdueTasks=" + overdueTasks +
                '}';
    }
}
