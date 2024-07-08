package com.example.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.Users;

@Repository
public interface OtpRepo extends JpaRepository<Users, Long>{
	
	Users findByEmail(String email);
	
}
