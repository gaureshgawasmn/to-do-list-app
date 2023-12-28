package com.techlab.todolist.controller;

import com.techlab.todolist.entities.Task;
import com.techlab.todolist.entities.User;
import com.techlab.todolist.repositories.TaskRepository;
import com.techlab.todolist.repositories.UserRepository;
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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<Task> addTask(@PathVariable long id, @RequestBody Task task) {
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

    @GetMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<Task> getTaskByTaskId(@PathVariable long id, @PathVariable long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent() && task.get().getUser().getId() == id) {
            return ResponseEntity.of(task);
        } else
            return ResponseEntity.notFound().build();
    }
}
