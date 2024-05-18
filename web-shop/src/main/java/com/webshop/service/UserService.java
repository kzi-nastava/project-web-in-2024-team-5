package com.webshop.service;

import com.webshop.dto.LoginDto;
import com.webshop.dto.UserDto;
import com.webshop.model.User;

/**
 * UserService
 */
public interface UserService {

    User authenticateUser(LoginDto loginDto);

    User save(UserDto user);
}
