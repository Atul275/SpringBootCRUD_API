package com.webapi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webapi.service.LoginService;
import org.springframework.http.ResponseEntity;

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
			return ResponseEntity.ok("Login successful" + user.getUsername());
		} else {
			System.out.println("Invalid username or password: " + user.getUsername());
			return ResponseEntity.status(401).body("Invalid username or password");
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Login user) {
		if (loginService.findByUserName(user.getUsername()) != null) {
			System.out.println("User:" + user.getUsername());
			return ResponseEntity.status(400).body("Username already exists");
		}
		loginService.saveUser(user);
		return ResponseEntity.ok("User registered successfully");
	}

	 @PostMapping("/verify-security")
	    public ResponseEntity<?> verifySecurityQuestion(@RequestBody SecurityQuestionDTO securityQuestionDTO) {
	        boolean isVerified = loginService.verifySecurityQuestion(securityQuestionDTO);
	        if (isVerified) {
	            return ResponseEntity.ok().body("Security question verified successfully.");
	        } else {
	            return ResponseEntity.badRequest().body("Security question verification failed.");
	        }
	    }

	@PostMapping("/forgot")
	public ResponseEntity<?> forgotPassword(@RequestBody String email) {
		String token = loginService.createPasswordResetToken(email);
		if (token != null) {
			// Send email with the token (implementation not shown)
			System.out.println("Password reset token sent to email: " + email);
			return ResponseEntity.ok("Password reset token sent to email");
		} else {
			return ResponseEntity.status(404).body("Email not found");
		}
	}
	
	 @PostMapping("/update-password")
	    public ResponseEntity<?> updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO) {
	        boolean isUpdated = loginService.updatePassword(passwordUpdateDTO);
	        if (isUpdated) {
	            return ResponseEntity.ok().body("Password updated successfully.");
	        } else {
	            return ResponseEntity.badRequest().body("Password update failed.");
	        }
	    }
	 
	@PostMapping("/reset")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
		boolean result = loginService.resetPassword(request.getToken(), request.getNewPassword());
		if (result) {
			return ResponseEntity.ok("Password reset successful");
		} else {
			return ResponseEntity.status(400).body("Invalid token or token expired");
		}
	}
}
