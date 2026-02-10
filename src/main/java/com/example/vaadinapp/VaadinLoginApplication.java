package com.example.vaadinapp;

import com.example.vaadinapp.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaadinLoginApplication implements ApplicationRunner {

    private final UserService userService;

    public VaadinLoginApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(VaadinLoginApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        userService.initializeSampleData();
    }
}