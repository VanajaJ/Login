package com.example.login.service;

import com.example.login.model.Users;

public interface OtpService {
	
	public Users registerUsers(Users user);
	
	public Users saveUser(Users user);

	public boolean verify(String email, String otp);
 
}