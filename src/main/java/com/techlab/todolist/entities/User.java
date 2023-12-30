package com.techlab.todolist.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "USER_DET")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique User id - Long", example = "1")
    private Long id;
    @Schema(description = "User name - String", example = "Ram")
    private String name;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Task> taskList = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public User addTask(Task task) {
        if (task != null) {
            this.taskList.add(task);
        }
        return this;
    }

    public User removeTask(Task task) {
        if (task != null) {
            this.taskList.remove(task);
        }
        return this;
    }
}
