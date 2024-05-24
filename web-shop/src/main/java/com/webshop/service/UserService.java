package com.webshop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.webshop.dto.ExtendedUserDto;
import com.webshop.dto.LoginDto;
import com.webshop.dto.ProfileDto;
import com.webshop.dto.UserDto;
import com.webshop.model.User;

/**
 * UserService
 */
public interface UserService {

    User authenticateUser(LoginDto loginDto);

    User save(UserDto user);

    User updateProfile(ExtendedUserDto user);

    User findById(Long id);

    List<ProfileDto> findAll(Pageable pageable);

    boolean comparePassword(Long id, String password);
}
