package com.webapi.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapi.model.Login;
import com.webapi.model.PasswordUpdateDTO;
import com.webapi.model.SecurityQuestionDTO;
import com.webapi.repository.LoignRepo;

@Service
public class LoginService {
	@Autowired
	private LoignRepo loginRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private Map<String, String> resetTokens = new HashMap<>();

	public Login findByUserName(String username) {
		return loginRepo.findByUsername(username);
	}

	public Login saveUser(Login user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return loginRepo.save(user);
	}

	public boolean checkPassword(String rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}

	public boolean verifySecurityQuestion(SecurityQuestionDTO securityQuestionDTO) {
		Login user = loginRepo.findByUsername(securityQuestionDTO.getUsername());
		if (user != null && user.getSques().equals(securityQuestionDTO.getSques())
				&& user.getAns().equals(securityQuestionDTO.getAns())) {
			return true;
		}
		return false;
	}

	public boolean updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
		Login user = loginRepo.findByUsername(passwordUpdateDTO.getUsername());
		if (user != null) {
			user.setPassword(passwordUpdateDTO.getNewPassword());
			loginRepo.save(user);
			return true;
		}
		return false;
	}

	public String createPasswordResetToken(String email) {
		Login user = loginRepo.findByUsername(email);
		if (user != null) {
			String token = UUID.randomUUID().toString();
			resetTokens.put(token, email);
			return token;
		}
		return null;
	}

	public boolean resetPassword(String token, String newPassword) {
		String email = resetTokens.get(token);
		if (email != null) {
			Login user = loginRepo.findByUsername(email);
			if (user != null) {
				user.setPassword(bCryptPasswordEncoder.encode(newPassword));
				loginRepo.save(user);
				resetTokens.remove(token);
				return true;
			}
		}
		return false;
	}
}
