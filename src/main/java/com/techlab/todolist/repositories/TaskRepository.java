package com.techlab.todolist.repositories;

import com.techlab.todolist.entities.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteByUserId(long id);
}
