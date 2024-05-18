package com.webshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webshop.dto.ExtendedUserDto;
import com.webshop.dto.LoginDto;
import com.webshop.dto.UserDto;
import com.webshop.model.Buyer;
import com.webshop.model.Seller;
import com.webshop.model.User;
import com.webshop.repository.BuyerRepository;
import com.webshop.repository.SellerRepository;
import com.webshop.repository.UserRepository;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private BCryptPasswordEncoder bEncoder;

    @Override
    public User authenticateUser(LoginDto loginDto) {
        User loggedUser = userRepository.findByUsername(loginDto.getUsername());

        if (loggedUser != null && bEncoder.matches(loginDto.getPassword(), loggedUser.getPassword()))
            return loggedUser;
        else
            return null;

    }

    @Override
    public User save(UserDto user) {
        if (userRepository.findByUsernameOrEmailOrPhoneNumber(user.getUsername(), user.getEmail(),
                user.getPhoneNumber()) != null) {
            throw new RuntimeException("User already exists");
        }
        try {
            User newUser;
            if (user.getRole().equals("buyer")) {
                Buyer buyer = new Buyer(user);
                buyer.setPassword(bEncoder.encode(buyer.getPassword()));
                newUser = buyerRepository.save(buyer);

            } else if (user.getRole().equals("seller")) {
                Seller seller = new Seller(user);
                seller.setPassword(bEncoder.encode(seller.getPassword()));
                newUser = sellerRepository.save(seller);

            } else {
                throw new IllegalArgumentException("Invalid user role: " + user.getRole());
            }

            return newUser;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to save user.", e);
        }
    }

    @Override
    public User updateProfile(ExtendedUserDto user) {

        try {
            User myUser = userRepository.findById(user.getId()).get();

            if (user.getName() != null)
                myUser.setName(user.getName());

            if (user.getLastname() != null)
                myUser.setLastname(user.getLastname());

            if (user.getUsername() != null)
                myUser.setUsername(user.getUsername());

            if (user.getPassword() != null)
                myUser.setPassword(bEncoder.encode(user.getPassword()));

            if (user.getEmail() != null)
                myUser.setEmail(user.getEmail());

            if (user.getPhoneNumber() != null)
                myUser.setPhoneNumber(user.getPhoneNumber());

            if (user.getDateOfBirth() != null)
                myUser.setDateOfBirth(user.getDateOfBirth());

            if (user.getProfilePicture() != null)
                myUser.setProfilePicture(user.getProfilePicture());

            if (user.getDescription() != null)
                myUser.setDescription(user.getDescription());

            return userRepository.save(myUser);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update user.", e);
        }
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        else
            return null;
    }

}
