package com.example.login.service.impl;

import com.example.login.service.EmailAndMobileService;
import com.example.login.service.EmailService;
import com.example.login.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailAndMobileServiceImpl implements EmailAndMobileService{
	

    @Autowired
    private EmailService emailService;

    //private static final String FIREBASE_WEB_API_KEY = "AIzaSyAIvV1RtRhKoFv6S0pSqOqVMnJTFvSK9NY";
   // @Autowired
   // private FirebaseApp firebaseApp;

    //@Autowired
   // private TwilioOTPService twilioOtpService; // Twilio service for SMS

    @Autowired
    private SmsService smsService;

    public void sendOtp(String email, String phoneNumber, String countryCode) {
        String otp = generateOtp();
        
        // Send OTP to email
        emailService.sendOtpEmail(email, otp);

        // Format the phone number with country code
        String formattedPhoneNumber = countryCode.trim().startsWith("+") 
            ? countryCode.trim() + phoneNumber.trim().replaceAll("\\s+", "") 
            : "+" + countryCode.trim() + phoneNumber.trim().replaceAll("\\s+", "");

        // Send OTP via SMS
        smsService.sendOtpSms(formattedPhoneNumber, otp);
    }

    private String generateOtp() {
        Random random = new Random();
        int otpLength = 6;
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
    

	/*
	 * private String createCustomToken(String phoneNumber, String otp) throws
	 * FirebaseAuthException { Map<String, Object> additionalClaims = new
	 * HashMap<>(); additionalClaims.put("phoneNumber", phoneNumber);
	 * additionalClaims.put("otp", otp);
	 * 
	 * return FirebaseAuth.getInstance(firebaseApp).createCustomToken(phoneNumber,
	 * additionalClaims); }
	 */

    @Override
    public boolean verifyOtp(String phoneNumber, String otp) {
        // Logic to verify OTP
        // This might involve checking the custom token's claims or verifying the OTP via Firebase's client-side SDK
        // Implement your verification logic here
        return true; // Return true if OTP is valid, false otherwise
    }
        
}
