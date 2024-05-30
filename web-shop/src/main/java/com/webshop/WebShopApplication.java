package com.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.webshop.model.Admin;
import com.webshop.repository.AdminRepository;

/*
   @SpringBootApplication anotacija nastala je od @EnableAutoConfiguration anotacije koja
   upravlja konfiguracijom aplikacije.
 */
@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }

    @Autowired
    private BCryptPasswordEncoder bEncoder;

    @Autowired
    private AdminRepository adminRepo;

    @Override
    public void run(String... args) {

        Admin admin = new Admin();

        admin.setName("Stefan");
        admin.setLastname("Crep");
        admin.setUsername("mojuser3");
        admin.setPassword(bEncoder.encode("stefan"));
        admin.setEmail("aleksa@gmail.com");
        admin.setPhoneNumber("0601234567");

        adminRepo.save(admin);
    }

}
