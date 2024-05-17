package com.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

/**
 * UserController
 */
@RestController("/api/v1")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto, HttpSession session) {
        if (loginDto.getPassword().isEmpty() || loginDto.getUsername().isEmpty())
            return new ResponseEntity<>("Invalid login data\n", HttpStatus.FORBIDDEN);

        return new ResponseEntity<>("Invalid login data\n", HttpStatus.FORBIDDEN);
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
