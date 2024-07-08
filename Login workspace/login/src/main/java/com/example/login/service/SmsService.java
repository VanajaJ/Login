package com.example.login.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class SmsService {

    @Value("${sms.api.key}")
    private String apiKey;

    @Value("${sms.api.url}")
    private String apiUrl;

    @Value("${twilio.accountSid}")
    private String accountSid;

    public void sendOtpSms(String phoneNumber, String otp) {
        try {
            String message = "Your OTP code is: " + otp;
            String data = "To=" + phoneNumber + "&From=your-twilio-phone-number&Body=" + message;

            String auth = accountSid + ":" + apiKey;
            String authHeader = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Authorization", authHeader)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201) {
                // Handle unsuccessful request
            }
        } catch (Exception e) {
            // Handle exception
        }
    }
}


/*@Service
public class SmsService {

	 private static final String FIREBASE_WEB_API_KEY = "AIzaSyAIvV1RtRhKoFv6S0pSqOqVMnJTFvSK9NY";

	    public void sendOtp(String phoneNumber) {
	        // Format the phone number with country code
	        String formattedPhoneNumber = phoneNumber.trim().startsWith("+") ? phoneNumber.trim() : "+" + phoneNumber.trim();

	        // Prepare the request payload
	        String payload = String.format("{\"phoneNumber\":\"%s\"}", formattedPhoneNumber);

	        // Set the headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json");

	        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

	        // Send OTP via Firebase REST API
	        RestTemplate restTemplate = new RestTemplate();
	        String url = "https://identitytoolkit.googleapis.com/v1/accounts:sendVerificationCode?key=" + FIREBASE_WEB_API_KEY;
	        try {
	            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	            System.out.println("OTP sent successfully to " + formattedPhoneNumber);
	            System.out.println("Response: " + response.getBody());
	        } catch (Exception e) {
	            System.err.println("Error sending OTP: " + e.getMessage());
	        }
	    }
}*/


