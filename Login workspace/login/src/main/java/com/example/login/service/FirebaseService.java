package com.example.login.service;

import com.example.login.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FirebaseService {

    private final JavaMailSender mailSender;
    //private FirebaseAuth firebaseAuth;

    public FirebaseService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void signUpWithEmailAndPhone(Users users) {
        String phoneNumber = users.getPhoneNumber();
        String countryCode = "+91";
        if (!isValidPhoneNumber(countryCode + phoneNumber)) {
            throw new IllegalArgumentException("Phone number must be a valid, E.164 compliant identifier starting with a '+' sign");
        }
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                .setEmail(users.getEmail())
                .setPhoneNumber(countryCode + phoneNumber);
        // Use Firebase Admin SDK to create the user
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(createRequest);
            System.out.println("Successfully created new user: " + userRecord.getUid());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating user" + e.getMessage());
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\+91\\d{10}");
    }

    public void sendOtp(String email, String phoneNumber) throws Exception {
        String countryCode = "+91"; // For now, using +91 as the default country code
        // Generate OTP logic here
        // For simplicity, assuming OTP is generated and sent through your preferred method
        String otpCode = generateOtp();

        // Send OTP via email and SMS (using Firebase Cloud Messaging or other service)
        sendOtpByEmail(email, otpCode);
        sendOtpByPhone(countryCode + phoneNumber, otpCode);
    }

    private void sendOtpByEmail(String email, String otpCode) throws Exception {
        jakarta.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMsg = "<h3>Your OTP code is: " + otpCode + "</h3>";
        helper.setText(htmlMsg, true);
		helper.setTo(email);
		helper.setSubject("Your OTP Code");
		helper.setFrom("vanaja15802@gmail.com");
		mailSender.send(mimeMessage);
    }

    private void sendOtpByPhone(String phoneNumber, String otpCode) {
        // Implement SMS sending logic using Firebase Cloud Messaging or other services
        // Example: send SMS with OTP code
        System.out.println("Sending OTP " + otpCode + " to phone: " + phoneNumber);
        // Add actual implementation here
    }

    private String generateOtp() {
        // Generate a 6-digit random OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public boolean verifyOtp(String otpCode, String userEnteredOtp) {
        // Implement OTP verification logic here
        // Typically involves comparing the entered OTP code with the expected value
        return otpCode.equals(userEnteredOtp); // Example, replace with actual verification logic
    }
}
