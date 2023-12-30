package com.techlab.todolist.controller;

import com.techlab.todolist.entities.Task;
import com.techlab.todolist.entities.User;
import com.techlab.todolist.repositories.TaskRepository;
import com.techlab.todolist.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping
    @Operation(summary = "Gets all users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets user by Id")
    public User getUserById(@Parameter(description = "User id to fetch user details", example = "1") @PathVariable long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create User")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping
    @Operation(summary = "Update User")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by Id")
    public ResponseEntity<String> deleteUser(@Parameter(description = "User id to delete user details", example = "1") @PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Add task for user")
    public ResponseEntity<Task> addTask(@Parameter(description = "User id to add task", example = "1") @PathVariable long id,
                                        @RequestBody Task task) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User tempUser = user.get();
            task.setUser(tempUser);
            taskRepository.save(task);
            tempUser.addTask(task);
            userRepository.save(tempUser);
            // Build the URI for the newly created task
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{taskId}")
                    .buildAndExpand(task.getId())
                    .toUri();
            return ResponseEntity.created(location).body(task);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/tasks")
    @Operation(summary = "Update task for user")
    public ResponseEntity<Task> updateTask(@Parameter(description = "User id to update task", example = "1") @PathVariable long id,
                                           @RequestBody Task task) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        if (optionalTask.isPresent() && optionalUser.isPresent() && optionalUser.get().getId() == optionalTask.get().getUser().getId()) {
            taskRepository.save(task);
            return ResponseEntity.of(Optional.ofNullable(task));
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/tasks/{taskId}")
    @Operation(summary = "Delete task for user using task id")
    public ResponseEntity<Void> deleteTask(@Parameter(description = "User id to delete task", example = "1") @PathVariable long id,
                                           @Parameter(description = "Task id to delete task", example = "1") @PathVariable long taskId) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent() && optionalUser.isPresent() && optionalUser.get().getId() == optionalTask.get().getUser().getId()) {
            optionalUser.get().removeTask(optionalTask.get());
            userRepository.save(optionalUser.get());
            taskRepository.delete(optionalTask.get());
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/tasks/{taskId}")
    @Operation(summary = "Gets task for user using task id")
    public ResponseEntity<Task> getTaskByTaskId(@Parameter(description = "User id to get task details", example = "1") @PathVariable long id,
                                                @Parameter(description = "Task id to get task details", example = "1") @PathVariable long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent() && task.get().getUser().getId() == id) {
            return ResponseEntity.of(task);
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Gets all tasks for user")
    public ResponseEntity<List<Task>> getAllTaskByUserId(@Parameter(description = "User id to get all task details", example = "1") @PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent() && !optionalUser.get().getTaskList().isEmpty()) {
            return ResponseEntity.of(Optional.ofNullable(optionalUser.get().getTaskList()));
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/tasks")
    @Operation(summary = "Delete all tasks for user")
    public ResponseEntity<Void> deleteAllTaskByUserId(@Parameter(description = "User id to delete all task details", example = "1") @PathVariable long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent() && !optionalUser.get().getTaskList().isEmpty()) {
            taskRepository.deleteByUserId(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
