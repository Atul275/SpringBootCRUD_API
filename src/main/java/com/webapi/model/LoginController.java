package com.webapi.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webapi.repository.LoignRepo;
import com.webapi.service.LoginService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login user) {
		Login existingUser = loginService.findByUserName(user.getUsername());
        if (existingUser != null && loginService.checkPassword(user.getPassword(), existingUser.getPassword())) {
        	 System.out.println("Login successful for user: " + user.getUsername());
            return ResponseEntity.ok("Login successful"+ user.getUsername());
        } else {
        	System.out.println("Login failed for user: " + user.getUsername());
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

	@PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Login user) {
        if (loginService.findByUserName(user.getUsername()) != null) {
        	System.out.println("User:" +user.getUsername());
            return ResponseEntity.status(400).body("Username already exists");
        }
        loginService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
