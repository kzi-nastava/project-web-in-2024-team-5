package com.webshop.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.dto.ExtendedUserDto;
import com.webshop.dto.LoginDto;
import com.webshop.dto.ProfileDto;
import com.webshop.dto.UserDto;
import com.webshop.model.User;
import com.webshop.service.UserServiceImpl;
import com.webshop.session.UserSession;

import jakarta.servlet.http.HttpSession;

/**
 * UserController
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * Funkcionalnost 1.5
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
    public ResponseEntity<ExtendedUserDto> loginUser(@RequestBody LoginDto loginDto, HttpSession session) {
        UserSession checkLoggedUser = (UserSession) session.getAttribute("User");
        if (checkLoggedUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (loginDto.getPassword().isEmpty() || loginDto.getUsername().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        User loggedUser = userService.authenticateUser(loginDto);
        if (loggedUser != null) {
            UserSession userSession = new UserSession(loggedUser);
            session.setAttribute("User", userSession);
            return ResponseEntity.ok(new ExtendedUserDto(loggedUser));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Funkcionalnost 1.4
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
    public ResponseEntity<ExtendedUserDto> registerUser(@Validated @RequestBody UserDto user, HttpSession session) {
        UserSession checkLoggedUser = (UserSession) session.getAttribute("User");
        if (checkLoggedUser != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        try {
            User nUser = userService.save(user);
            UserSession userSession = new UserSession(nUser);
            session.setAttribute("User", userSession);
            return ResponseEntity.ok(new ExtendedUserDto(nUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExtendedUserDto());
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<String> Logout(HttpSession session) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null)
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);

        session.invalidate();
        return ResponseEntity.ok("Successfully logged out!\n");
    }

    /**
     * Azuriranje profila korisnika
     * Funkcionalnosti 2.1 i 3.1
     *
     * @param user
     * @param session
     * @return
     */
    @PatchMapping("/users/me")
    public ResponseEntity<ExtendedUserDto> updateProfile(@RequestBody ExtendedUserDto user, HttpSession session) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        user.setId(loggedUser.getId());

        try {

            User updatedUser = userService.updateProfile(user);
            return ResponseEntity.ok(new ExtendedUserDto(updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/users/me")
    public ResponseEntity<ExtendedUserDto> getMyUser(HttpSession session) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            User user = userService.findById(loggedUser.getId());
            return ResponseEntity.ok(new ExtendedUserDto(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ProfileDto> getUser(HttpSession session, @PathVariable Long id) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            User user = userService.findById(id);
            if (Objects.equals(id, loggedUser.getId())) {
                URI location = URI.create("/api/v1/users/me");
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", location.toString())
                        .build();
            }

            ProfileDto profileDto = new ProfileDto(user);
            return ResponseEntity.ok(profileDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<ProfileDto>> getUsers(HttpSession session, Pageable pageable) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            List<ProfileDto> pDtos = userService.findAll(pageable);
            return ResponseEntity.ok(pDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/me/password")
    public ResponseEntity<Void> checkMyPassword(@Validated @RequestBody LoginDto lDto, HttpSession session) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (userService.comparePassword(loggedUser.getId(), lDto.getPassword()))
            return ResponseEntity.ok().build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
