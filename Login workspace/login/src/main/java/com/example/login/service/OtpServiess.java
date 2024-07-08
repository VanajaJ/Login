package com.example.login.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OtpServiess {
	
	 	@Autowired
	    private JavaMailSender mailSender;
	  
	    @Value("${twilio.phone.number}")
	    private String twilioPhoneNumber;

	    public OtpServiess(
	    		@Value("${twilio.accountSid}") 
	    		String accountSid,
	            
	    		@Value("${twilio.authToken}") 
	    		String authToken) {
	        Twilio.init(accountSid, authToken);
	    }
	    
	    public String generateOtps() {
	        Random random = new Random();
	        int otp = 100000 + random.nextInt(900000);
	        return String.valueOf(otp);
	    }

	    public void sendEmailOtp(String email, String otp) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("Your OTP Code");
	        message.setText("Your OTP code is: " + otp);
	        mailSender.send(message);
	    }

	    public void sendSmsOtp(String phoneNumber, String sms) {
	        Message message = Message.creator(
	                new PhoneNumber(phoneNumber),  // To number
	                new PhoneNumber(twilioPhoneNumber),  // From number
	                "Your OTP code is: " + sms)  // SMS body
	            .create();
	    }
	   
	    
	    public boolean verifyOtp(String phoneNumber, String sms) {
	        // Logic to verify OTP
	        // This might involve checking the custom token's claims or verifying the OTP via Firebase's client-side SDK
	        // Implement your verification logic here
	        return true; // Return true if OTP is valid, false otherwise
	    }
	     
	    public boolean verify(String email, String otp) { 
			String storedOtp = otpStorage.get(email); 
			if (storedOtp != null && storedOtp.equals(otp)) {
				clearOtp(email); // Remove OTP from storage after successful verification
				return true; 
			}
		return false; 
		}
	 
	    private final Random random = new Random();
	    
		private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
	    
		public void storeOtp(String email, String otp) {
			otpStorage.put(email, otp);
		}

		public void clearOtp(String email) { 
			otpStorage.remove(email); 
		}
	 
		public String generateOtp() { 
			int otp = 100000 + random.nextInt(900000); //Generate 6-digit OTP 
			return String.valueOf(otp); 
		}
		
	 
		public String getOtp(String email) {
			return otpStorage.get(email); 
		}
}
