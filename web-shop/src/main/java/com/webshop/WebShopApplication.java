package com.webshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.webshop.model.Admin;

/*
   @SpringBootApplication anotacija nastala je od @EnableAutoConfiguration anotacije koja
   upravlja konfiguracijom aplikacije.
 */
@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }

}
