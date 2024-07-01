package com.example.login.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column
    private String userName;
    
    @Column
    private String email;
    
    @Column
    private String otp;
    
    @Column
    private String password;
    
    @Column
    private String countryCode;
    
    @Column
    private String phoneNumber;
    
    @Column
    private String sms;
    
    @Column
    private boolean verified;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCountryCode() {
        return countryCode;
    }
	
	public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Users() {
	}

	public Users(Long userId, String userName, String email, String otp, String password, String countryCode,
			String phoneNumber, String sms, boolean verified) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.otp = otp;
		this.password = password;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.sms = sms;
		this.verified = verified;
	}
}
