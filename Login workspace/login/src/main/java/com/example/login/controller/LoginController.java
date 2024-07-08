package com.example.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.Users;
import com.example.login.repo.OtpRepo;
import com.example.login.service.EmailAndMobileService;
import com.example.login.service.OtpService;
import com.example.login.service.OtpServiess;
import com.example.login.service.TwilioOTPService;

@RestController
@RequestMapping("/users")
public class LoginController {

	@Autowired
	private OtpService otpService;
	
	@Autowired
	private OtpRepo otpRepo;
	
	@Autowired 
	private TwilioOTPService twilioOTPService;
	
	@Autowired
	private EmailAndMobileService emailAndMobileService;
	
	@Autowired
	private OtpServiess otpServiess;
	
	@PostMapping("/register")
	public ResponseEntity<Users> registerUsers(@RequestBody Users users) {
		return ResponseEntity.ok(otpService.registerUsers(users));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Users> login(@RequestBody Users users) {
		//otpService.
		return ResponseEntity.ok(otpService.saveUser(users));
	}
	
	 @PostMapping("/verify")
	    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
	        try {
	            otpService.verify(email, otp);
	            return ResponseEntity.ok("OTP verified successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	 
	 
	 @PostMapping("/mobile")
	    public String generateAndSendMobileOTP(@RequestBody Users users) {
	        String otp = generateOTP(); // Implement your OTP generation logic
	        twilioOTPService.sendOTP(users.getCountryCode(), users.getPhoneNumber(), otp);
	        return "OTP sent successfully";
	    }

	    private String generateOTP() {
	        // Implement your OTP generation logic here
	        // For simplicity, returning a fixed OTP
	        return "123456";
	    }
	    
	    @PostMapping("/verifysms")
	    public ResponseEntity<String> verifysms(@RequestParam String phoneNumber, @RequestParam String otp) {
	        try {
	        	twilioOTPService.verifyOTP(phoneNumber, otp);
	            return ResponseEntity.ok("OTP verified successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }  																																															
	    
	    @PostMapping("/registeruser")
	    public String register(@RequestBody Users users) {
	        try {
	            // Save user details to the database
	            otpService.saveUser(users);
	            
	            // Send OTP
	            emailAndMobileService.sendOtp(users.getEmail(), users.getPhoneNumber(), users.getCountryCode());
	            
	            System.out.println("send to otp " + users.getEmail());
	            System.out.println("send to otp " + users.getPhoneNumber());
	            System.out.println("sent to otp"  + users.getOtp());
	            return "Registration successful! OTP sent to email and phone.";
	        } catch (Exception e) {
	            return "Registration failed: " + e.getMessage();
	        }
	    }		
	    
	    @PostMapping("/verifyOtp")
	    public ResponseEntity<String> verify(@RequestParam String phoneNumber, @RequestParam String otp) {
	        boolean isOtpValid = emailAndMobileService.verifyOtp(phoneNumber, otp);
	        if (isOtpValid) {
	            return ResponseEntity.ok("OTP verification successful!");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OTP verification failed!");
	        }
	    }
	    
	    @PostMapping("/send-otp")
	    public String sendOtp(@RequestParam String email, @RequestParam String phoneNumber,@RequestParam String userName, @RequestParam String password) {
	        String otp = otpServiess.generateOtps();
	        String sms = otpServiess.generateOtps();

	        otpServiess.sendEmailOtp(email, otp);
	        otpServiess.sendSmsOtp(phoneNumber, sms);

	        Users users = new Users(email, otp, phoneNumber, sms);
	        System.out.println("send to otp " + sms);
	        otpRepo.save(users);

	        return "OTP sent to email and phone";
	    }

	    @PostMapping("/verify-sms")
	    public ResponseEntity<String> smsverify(@RequestParam String phoneNumber, @RequestParam String sms) {
	        boolean isOtpValid = otpServiess.verifyOtp(phoneNumber, sms);
	        if (isOtpValid) {
	            return ResponseEntity.ok("OTP verification successful!");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OTP verification failed!");
	        }
	    }
	    
	    @PostMapping("/verify-otp")
	    public ResponseEntity<String> otpverify(@RequestParam String email, @RequestParam String otp) {
	        try {
	        	otpServiess.verify(email, otp);
	            return ResponseEntity.ok("OTP verified successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	
}