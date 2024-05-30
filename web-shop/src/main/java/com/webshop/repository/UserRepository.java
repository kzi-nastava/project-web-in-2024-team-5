package com.webshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

    User findById(Long id);

    User save(User user);

    User findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);

    @Query(value = "SELECT * FROM users WHERE user_role <> 'admin'", countQuery = "SELECT count(*) FROM users WHERE user_role <> 'admin'", nativeQuery = true)
    Page<User> findAllNonAdminUsers(Pageable pageable);

}
