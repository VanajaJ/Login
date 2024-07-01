/*
 * package com.example.login.service.impl;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.stereotype.Service;
 * 
 * import com.example.login.model.Users; import com.example.login.repo.OtpRepo;
 * //import com.example.login.service.EmailService; import
 * com.example.login.service.OtpService; import
 * com.example.login.service.TwilioOTPService;
 * 
 * import java.security.SecureRandom; import java.time.LocalDateTime; import
 * java.util.Map; import java.util.Optional; import java.util.Random; import
 * java.util.concurrent.ConcurrentHashMap;
 * 
 * @Service public class OtpServiceImpl implements OtpService{
 * 
 * @Autowired private OtpRepo otpRepo;
 * 
 * @Autowired private EmailService emailService;
 * 
 * @Autowired private JavaMailSender mailSender;
 * 
 * private TwilioOTPService twilioOTPService;
 * 
 * private final Random random = new Random();
 * 
 * private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
 * 
 * //UserSave and Otp generatedCode
 * 
 * @Override
 * 
 * public Users saveUser(Users user) { String otp = generateOTP();
 * user.setOtp(otp); Users savedUser = otpRepo.save(user);
 * sendVerificationEmail(savedUser.getEmail(), otp); return savedUser; }
 * 
 * 
 * 
 * private String generateOTP() { Random random = new Random(); int otpLength =
 * 6; StringBuilder otp = new StringBuilder(); for (int i = 0; i < otpLength;
 * i++) { otp.append(random.nextInt(10)); } return otp.toString(); }
 * 
 * 
 * private void sendVerificationEmail(String email,String otp){ String subject =
 * "Email verification"; String body ="your verification otp is: "+otp;
 * emailService.sendEmail(email,subject,body); }
 * 
 * 
 * //Otp Verification Code
 * 
 * @Override public boolean verify(String email, String otp) { String storedOtp
 * = otpStorage.get(email); if (storedOtp != null && storedOtp.equals(otp)) {
 * clearOtp(email); // Remove OTP from storage after successful verification
 * return true; } return false; }
 * 
 * public void storeOtp(String email, String otp) { otpStorage.put(email, otp);
 * }
 * 
 * public void clearOtp(String email) { otpStorage.remove(email); }
 * 
 * public String generateOtp() { int otp = 100000 + random.nextInt(900000); //
 * Generate 6-digit OTP return String.valueOf(otp); }
 * 
 * public String getOtp(String email) { return otpStorage.get(email); }
 * 
 * 
 * 
 * //mobile otp code // Assuming OTPRepository handles database operations
 * 
 * 
 * @Override public void sendOTP(String mobileNumber) { // Generate a 6-digit
 * OTP String otp = generateOTP();
 * 
 * // Send OTP via SMS using Twilio twilioOTPService.sendSMS(mobileNumber,
 * "Your OTP is: " + otp);
 * 
 * // Save OTP in database Users otpEntity = new Users();
 * otpEntity.setPhoneNumber(mobileNumber); otpEntity.setOtp(otp);
 * otpEntity.setCreatedAt(LocalDateTime.now()); otpRepo.save(otpEntity); }
 * 
 * }
 */