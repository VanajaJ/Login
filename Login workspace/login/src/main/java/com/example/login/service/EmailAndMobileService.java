package com.example.login.service;

import com.google.firebase.auth.FirebaseAuthException;

public interface EmailAndMobileService {
	
	//public void sendOtp(String email, String phoneNumber) throws FirebaseAuthException;
	
	public void sendOtp(String email, String phoneNumber, String countryCode) throws FirebaseAuthException ;
	
	public boolean verifyOtp(String phoneNumber, String otp);
	
}
