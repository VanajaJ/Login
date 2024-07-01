package com.example.login.service;

import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;

@Service
public class TwilioOTPService {

	@Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String fromPhoneNumber;

    public void sendOTP(String countryCode, String mobileNumber, String otp) {
        try {
            Twilio.init(accountSid, authToken);

            // Clean up the country code if it contains a '+'
            if (countryCode.startsWith("+")) {
                countryCode = countryCode.substring(1);
            }

            // Concatenate country code and mobile number
            String toPhoneNumber = "+" + countryCode + mobileNumber;
            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber), // To
                    new PhoneNumber(fromPhoneNumber), // From
                    "Your OTP is: " + otp) // Message
                .create();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send OTP", e);
        }
    }

	/*
	 * public boolean verifyOTP(String phoneNumber, String otp) { String storedOTP =
	 * otpMap.get(phoneNumber); return storedOTP != null && storedOTP.equals(otp); }
	 */
	/*
	 * @Value("${twilio.account.sid}") private String accountSid;
	 * 
	 * @Value("${twilio.auth.token}") private String authToken;
	 * 
	 * @Value("${twilio.phone.number}") private String phoneNumber;
	 * 
	 * public void sendSMS(String to, String body) { Twilio.init(accountSid,
	 * authToken);
	 * 
	 * Message message = Message.creator( new com.twilio.type.PhoneNumber(to), new
	 * com.twilio.type.PhoneNumber(phoneNumber), body) .create();
	 * 
	 * System.out.println("SMS sent successfully with message SID: " +
	 * message.getSid()); }
	 */
    
}


/*
 * @Service public class TwilioOTPService {
 * 
 * @Value("${twilio.account.sid}") private String accountSid;
 * 
 * @Value("${twilio.auth.token}") private String authToken;
 * 
 * @Value("${twilio.phone.number}") private String twilioPhoneNumber;
 * 
 * // Map to store OTPs temporarily (for demo purposes) private Map<String,
 * String> otpMap = new HashMap<>();
 * 
 * public void sendOTP(String phoneNumber, String otp) { try {
 * Twilio.init(accountSid, authToken);
 * 
 * Message message = Message.creator( new PhoneNumber(phoneNumber), new
 * PhoneNumber(twilioPhoneNumber), "Your OTP for verification is: " + otp)
 * .create();
 * 
 * System.out.println("OTP sent successfully with SID: " + message.getSid()); }
 * catch (Exception e) { e.printStackTrace(); } }
 * 
 * public boolean verifyOTP(String phoneNumber, String otp) { String storedOTP =
 * otpMap.get(phoneNumber); return storedOTP != null && storedOTP.equals(otp); }
 * 
 * }
 */
		