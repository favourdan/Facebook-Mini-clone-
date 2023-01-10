package com.example.spring_boot_demo.repository;

import com.example.spring_boot_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository<Login> extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);
}
