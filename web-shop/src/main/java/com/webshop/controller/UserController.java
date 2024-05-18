package com.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.User;
import com.webshop.dto.LoginDto;
import com.webshop.service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

/**
 * UserController
 */
@RestController("/api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl UserService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto, HttpSession session) {
        if (loginDto.getPassword().isEmpty() || loginDto.getUsername().isEmpty())
            return new ResponseEntity<>("Please provide both username and password\n", HttpStatus.BAD_REQUEST);

        User loggedUser = UserService.authenticateUser(loginDto);
        if (loggedUser != null) {
            session.setAttribute("user", loggedUser);
            return ResponseEntity.ok("Successfully logged in!");
        }

        return new ResponseEntity<>("User does not exist!", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(HttpSession session) {

        return new ResponseEntity<>("Invalid login data\n", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {

        return new ResponseEntity<>("Invalid login data\n", HttpStatus.FORBIDDEN);
    }
}
