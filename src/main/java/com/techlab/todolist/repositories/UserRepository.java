package com.techlab.todolist.repositories;

import com.techlab.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
