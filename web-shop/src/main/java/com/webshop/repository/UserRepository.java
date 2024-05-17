package com.webshop.repository;
import com.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    List<User> findByUsername(String username);
    List<User> findByEmailAndLastname(String email, String lastname);
    List<User> findDistinctPeopleByNameOrLastname(String firstname, String lastname);
    
}