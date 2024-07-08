package com.example.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service; 

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber; 

@Service 
public class TwilioOTPService {

	@Value("${twilio.accountSid}")
	private String accountSid;

	@Value("${twilio.authToken}") 
	private String authToken;
	
	@Value("${twilio.phoneNumber}")
	private String fromPhoneNumber;
	
	private Map<String,String> otpMap = new HashMap<>();
	
	public void sendOTP(String countryCode, String mobileNumber, String otp) {
		try { 
			Twilio.init(accountSid, authToken);
		// Clean up the country code if it contains a '+'
		if(countryCode.startsWith("+")) {
			countryCode = countryCode.substring(1); 
		}
		// Concatenate country code and mobile number
		 String toPhoneNumber = "+" + countryCode + mobileNumber;
		 Message message = Message.creator(new PhoneNumber(toPhoneNumber), // To 
				 new PhoneNumber(fromPhoneNumber), // From
				 "Your OTP is: " + otp) // Message 
				 .create(); 
		 } catch (Exception e) { 
			 throw new RuntimeException("Failed to send OTP", e); 
		} 
	}
 
	public boolean verifyOTP(String phoneNumber, String otp) { 
		String storedOTP = otpMap.get(phoneNumber); 
		return storedOTP != null && storedOTP.equals(otp); 
	}
	
	
	//firebase sms implementaion
	
	 @Value("${twilio.phoneNumber}")
	    private String twilioPhoneNumber;

	    public boolean sendSmsOtp(String phoneNumber, String sms, String countryCode) {
	        try {
	            // Format the phone number with country code
	            String formattedPhoneNumber = countryCode.startsWith("+") 
	                ? countryCode + phoneNumber 
	                : "+" + countryCode + phoneNumber;
	            
	            // Send SMS using Twilio
	            Message message = Message.creator(
	                    new PhoneNumber(formattedPhoneNumber),
	                    new PhoneNumber(twilioPhoneNumber),
	                    "Your OTP is: " + sms)
	                .create();

	            System.out.println("SMS sent successfully to " + formattedPhoneNumber);
	            return true;
	        } catch (ApiException e) {
	            System.err.println("Error sending SMS: " + e.getMessage());
	            // Handle exception as needed
	            return false;
	        }
	    }
	 
	  private String formatPhoneNumber(String phoneNumber) {
	        // Ensure the phone number format is correct for Twilio
	        // For example, remove spaces, hyphens, and add the country code if necessary
	        // Example: "+91XXXXXXXXXX" for India
	        return phoneNumber.replaceAll("[^0-9]", ""); // Example implementation, adjust as per your needs
	    }
	
}
