package com.webapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.webapi.model.Login;

@Repository
public interface LoignRepo extends JpaRepository<Login, Integer> {
	Login findByUsernameOrEmail(String username, String email);

	Login findByUsername(String username);// Added

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	@Query(value = "SELECT * FROM login  WHERE username= ?1 and password = ?2", nativeQuery = true)
	Login existByUserNamePassword(String username, boolean password);

//	Optional<Login> findByUsername(String username);
}
