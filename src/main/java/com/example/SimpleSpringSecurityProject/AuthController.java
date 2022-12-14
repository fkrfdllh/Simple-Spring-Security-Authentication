package com.example.SimpleSpringSecurityProject;

import com.example.SimpleSpringSecurityProject.models.AuthenticationRequest;
import com.example.SimpleSpringSecurityProject.models.AuthenticationResponse;
import com.example.SimpleSpringSecurityProject.models.UserModel;
import com.example.SimpleSpringSecurityProject.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception ex) {
            return ResponseEntity.ok(new AuthenticationResponse("Error during client authentication: " + ex.getMessage()));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Client authenticated: " + username));
    }

    @PostMapping("/subs")
    private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {

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

        return ResponseEntity.ok(new AuthenticationResponse("Subscribe Success for client: " + username + " with password '" + password + "'"));
    }
}