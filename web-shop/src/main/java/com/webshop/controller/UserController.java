package com.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.dto.LoginDto;
import com.webshop.dto.UserDto;
import com.webshop.model.User;
import com.webshop.service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

/**
 * UserController
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl UserService;

    /**
     * Funkcionalnost 1.5
     *
     * Ocekuje ovakav body:
     * {
     * "username": "mojuser",
     * "password": "password"
     * }
     * 
     * @param loginDto
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto, HttpSession session) {
        if (loginDto.getPassword().isEmpty() || loginDto.getUsername().isEmpty())
            return new ResponseEntity<>("Please provide both username and password\n", HttpStatus.BAD_REQUEST);

        User loggedUser = UserService.authenticateUser(loginDto);
        if (loggedUser != null) {
            session.setAttribute("User", loggedUser);
            return ResponseEntity.ok("Successfully logged in!");
        }

        return new ResponseEntity<>("User does not exist!", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Funkcionalnost 1.4
     *
     * Ocekuje ovakav body:
     * {
     * "name":"aleksa",
     * "lastname":"mrda",
     * "username":"mojuser",
     * "password":"password",
     * "email":"email@gmail.com",
     * "phoneNumber":"0601234567",
     * "role":"buyer"
     * }
     * 
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto user, HttpSession session) {
        System.out.println(user);
        if (!isInformationProvided(user)) {
            return new ResponseEntity<>("Please provide necessary information\n", HttpStatus.BAD_REQUEST);
        }

        try {
            User newUser = UserService.save(user);
            session.setAttribute("User", newUser);
            return new ResponseEntity<>("User registered successfully\n", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register user\n", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<String> Logout(HttpSession session) {
        User loggedUser = (User) session.getAttribute("User");

        if (loggedUser == null)
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return ResponseEntity.ok("Successfully logged out!\n");
    }

    private boolean isInformationProvided(UserDto user) {
        if (user.getUsername() == null || user.getEmail() == null || user.getPhoneNumber() == null
                || user.getPassword() == null || user.getName() == null || user.getLastname() == null)
            return false;

        return true;
    }
}
