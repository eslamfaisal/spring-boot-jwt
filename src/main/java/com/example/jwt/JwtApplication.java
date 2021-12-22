package com.example.jwt;

import com.example.jwt.utils.JwtUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtApplication implements CommandLineRunner {

    final JwtUtils jwtUtils;

    public JwtApplication(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("JWT Token: " + jwtUtils.getSECRET_KEY());
    }
}
