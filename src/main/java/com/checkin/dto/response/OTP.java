package com.checkin.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OTP {
    private int id;
    private String otp;
    private LocalDateTime expiryTime;
    private String email;
    private long counter;

    // Getters and setters
}
