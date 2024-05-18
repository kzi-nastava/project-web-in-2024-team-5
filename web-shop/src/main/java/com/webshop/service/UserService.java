package com.webshop.service;

import com.webshop.dto.LoginDto;
import com.webshop.model.User;

/**
 * UserService
 */
public interface UserService {

    User authenticateUser(LoginDto loginDto);
}
