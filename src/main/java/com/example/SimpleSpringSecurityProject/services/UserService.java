package com.example.SimpleSpringSecurityProject.services;

import com.example.SimpleSpringSecurityProject.models.UserModel;
import com.example.SimpleSpringSecurityProject.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel foundedUser = userRepository.findByUsername(username);

        if (foundedUser == null) {
            return null;
        }

        String userUsername = foundedUser.getUsername();
        String userPassword = foundedUser.getPassword();

        return new User(userUsername, userPassword, new ArrayList<>());
    }
}