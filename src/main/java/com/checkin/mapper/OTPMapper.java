package com.checkin.mapper;

import com.checkin.dto.response.OTP;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OTPMapper {
    void saveOTP(OTP otp);

    OTP validateOTP(String otp, String email);
}
