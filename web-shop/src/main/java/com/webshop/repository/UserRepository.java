package com.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);

    User findByUsername(String username);

    List<User> findByEmailAndLastname(String email, String lastname);

    List<User> findDistinctPeopleByNameOrLastname(String firstname, String lastname);

    User findByUsernameAndPassword(String username, String password);

}
