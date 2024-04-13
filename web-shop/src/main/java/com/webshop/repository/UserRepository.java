package com.webshop.repository;
import com.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    List<User> findByUsername(String username);
    List<User> findByEmailAndLastname(String email, String lastname);
    List<User> findDistinctPeopleByNameOrLastname(String firstname, String lastname);
    
}