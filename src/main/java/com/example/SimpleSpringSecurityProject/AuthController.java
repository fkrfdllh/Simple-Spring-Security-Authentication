package com.example.SimpleSpringSecurityProject;

import com.example.SimpleSpringSecurityProject.models.AuthenticationRequest;
import com.example.SimpleSpringSecurityProject.models.AuthenticationResponse;
import com.example.SimpleSpringSecurityProject.models.UserModel;
import com.example.SimpleSpringSecurityProject.models.UserRepository;
import jdk.jfr.internal.EventWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    /**
     * UserRepository for manage MongoDB data
     */
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {

    }

    @PostMapping("/subs")
    private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {
        /*
          AuthenticationRequest for get request data
          */
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(password);

        try {
            userRepository.save(userModel);
        } catch (Exception ex) {
            return ResponseEntity.ok(new AuthenticationResponse("Subscribe Success for client: " + username));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Subscribe Success for client: " + username));
    }
}