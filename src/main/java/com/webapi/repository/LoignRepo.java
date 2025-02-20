package com.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webapi.model.Login;

@Repository
public interface LoignRepo extends JpaRepository<Login, Integer> {
	Login findByUsernameOrEmail(String username, String email);

	Login findByUsername(String username);
}
