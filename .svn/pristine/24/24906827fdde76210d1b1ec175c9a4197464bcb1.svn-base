package com.arora.account.repository;

import com.arora.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUseremail(String email);
    User findById(Long id);
    
}