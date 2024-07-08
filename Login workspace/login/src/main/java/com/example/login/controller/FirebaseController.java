package com.example.login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.Users;
import com.example.login.service.EmailService;
import com.example.login.service.OtpService;

@RestController
@RequestMapping("/api")
public class FirebaseController {
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	private OtpService otpService;

    private Map<String, String> otpStorage = new HashMap<>();

    @PostMapping("/send-email-otp")
    public String register(@RequestBody Users users) {
        String otp = generateOtp();
        otpStorage.put(users.getEmail(), otp);
        emailService.sendOtp(users.getEmail(), otp);
        otpService.registerUsers(users);
        return "OTP sent to email";
    }
    
    @PostMapping("/verify-email-otp")
    public String verifyEmailOtp(@RequestBody Users users) {
        String storedOtp = otpStorage.get(users.getEmail());
        if (storedOtp != null && storedOtp.equals(users.getOtp())) {
            return "OTP verified";
        } else {
            return "Invalid OTP";
        }
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

}
