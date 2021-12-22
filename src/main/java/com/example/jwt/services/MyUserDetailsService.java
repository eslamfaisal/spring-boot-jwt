package com.example.jwt.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    String email = "eslam";
    String password = "pass";

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("UserName: " + userName);
        return new User(userName, "pass", new ArrayList<>());
    }

//
//    public UserDetails loadUserByUsername(String email, String password) throws UsernameNotFoundException {
//
//        System.out.println("email: " + email + " password: " + password);
//        return new User(this.email, this.password, new ArrayList<>());
//    }


}
