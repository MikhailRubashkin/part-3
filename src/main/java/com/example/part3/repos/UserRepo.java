package com.example.part3.repos;

import com.example.part3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
