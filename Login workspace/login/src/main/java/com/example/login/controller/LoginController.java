package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.Users;
import com.example.login.service.FirebaseService;
//import com.example.login.service.OtpService;
import com.example.login.service.TwilioOTPService;


@RestController
@RequestMapping("/users")
public class LoginController {

	//@Autowired
	//private OtpService otpService;
	
	@Autowired
	private TwilioOTPService twilioOTPService;
	
	@Autowired
	private FirebaseService firebaseService;
	
	@PostMapping("/signup")
    public ResponseEntity<String> signUpWithEmailAndPhone(@RequestBody Users users) {
        try {
            firebaseService.signUpWithEmailAndPhone(users);
            return ResponseEntity.ok("Signup successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed");
        }
    }

	/*
	 * @PostMapping("/verify-otp") public ResponseEntity<String>
	 * verifyOtp(@RequestParam String otpCode) { if
	 * (firebaseService.verifyOtp(otpCode)) { // Perform additional logic here if
	 * needed (e.g., mark email and phone as verified) return
	 * ResponseEntity.ok("OTP verified successfully"); } else { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP"); } }
	 */
	
		
	/*@PostMapping("/login")
	public ResponseEntity<Users> registerUser(@RequestBody Users users) {
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
	    
	    
	    */
	    
	    
	    
		/*
		 * @PostMapping("/signup") public String signUp(@RequestBody Users user) throws
		 * FirebaseAuthException { UserRecord.CreateRequest request = new
		 * UserRecord.CreateRequest() .setEmail(user.getEmail())
		 * .setPassword(user.getPassword()) .setPhoneNumber(user.getPhoneNumber());
		 * 
		 * UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
		 * return userRecord.getUid(); }
		 * 
		 * @PostMapping("/signin") public String signIn(@RequestBody Users user) { //
		 * Implement sign-in logic with Firebase Admin SDK if needed return
		 * "Sign-in not implemented on backend"; }
		 */
	 
		/*
		 * @PostMapping("/verifysms") public String verifyOtp(@RequestParam Long
		 * userId, @RequestParam String otp) { boolean isVerified =
		 * otpService.verifyOtp(userId, otp); if (isVerified) { return
		 * "OTP verified successfully!"; } else { return "Invalid OTP!"; } }
		 */
	 

		/*
		 * @PostMapping("/mobile") public ResponseEntity<String>
		 * generateAndSendMobileOTP(@RequestParam String mobileNumber) { String otp =
		 * generateOTP(); twilioOTPService.sendOTP(mobileNumber, otp);
		 * System.out.println("OTP sent to mobile number: " + otp); return
		 * ResponseEntity.ok("OTP sent to mobile number: " + mobileNumber); }
		 * 
		 * private String generateOTP() { // Implement OTP generation logic (e.g.,
		 * random number) return String.valueOf((int) ((Math.random() * 9000) + 1000));
		 * }
		 * 
		 * @PostMapping("/verifysms") public ResponseEntity<String>
		 * verifyOTP(@RequestParam String mobileNumber, @RequestParam String otp) { try
		 * { otpService.verify(mobileNumber, otp); return
		 * ResponseEntity.ok("OTP verified successfully"); } catch (RuntimeException e)
		 * { return ResponseEntity.badRequest().body(e.getMessage()); } }
		 */

}