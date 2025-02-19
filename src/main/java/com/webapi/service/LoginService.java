package com.webapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapi.model.Login;
import com.webapi.repository.LoignRepo;

@Service
public class LoginService {
	@Autowired
	private LoignRepo loginRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
}
