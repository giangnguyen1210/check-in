package com.checkin.service.impl;
import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.AuthResponse;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.OTP;
import com.checkin.dto.response.UserResponse;
import com.checkin.mapper.OTPMapper;
import com.checkin.mapper.UserMapper;
import com.checkin.security.JwtGenerator;
import com.checkin.service.AuthService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.*;
import java.time.Duration;
import java.time.LocalDateTime;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private OTPMapper otpMapper;
    private static final String SECRET_KEY = "check-in"; // Thay thế bằng khoá bí mật thực tế của bạn

    @Override
    public BaseResponse login(UserRequest request) {
        UserResponse user = userMapper.findByEmail(request.getEmail());
        AuthResponse authResponse = new AuthResponse("");
        if(user==null){
            authResponse.setErrorCode(HttpStatus.NOT_FOUND.name());
            authResponse.setErrorDesc("User not found");
            return authResponse;
        }
        if(request.getEmail()==null || request.getPassword()==null){
            authResponse.setErrorCode("Email or password is empty");
        }else{
            if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
                );
                System.out.printf(authentication.getName());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtGenerator.generateToken(authentication);
                Claims claims = JwtGenerator.getEmailFromJwt(token);

                // Lấy thông tin người dùng
                String username = claims.getSubject();
                authResponse.setAccessToken(token);
                authResponse.setData(username);
                authResponse.setErrorCode(HttpStatus.OK.name());
            }else{
                authResponse.setErrorCode("Mat khau khong trung khop");
            }

        }
        return authResponse;
    }

    public BaseResponse sendEmailOTP(UserRequest request) throws NoSuchAlgorithmException {
        BaseResponse baseResponse = new BaseResponse();
        // check email exist:
        UserResponse checkEmailExist = userMapper.findByEmail(request.getEmail());
        if(checkEmailExist==null){
            baseResponse.setErrorCode(HttpStatus.NOT_FOUND.name());
            baseResponse.setErrorDesc("Email is not exist");
            return baseResponse;
        }else{
            String otp = generateOTP();
            LocalDateTime expiryTime = LocalDateTime.now().plusSeconds(30); // Thời gian hết hạn: 30 giây
            long currentCounter = System.currentTimeMillis() / 30000;
            OTP otpEntity = new OTP();
            otpEntity.setOtp(otp);
            otpEntity.setExpiryTime(expiryTime);
            otpEntity.setEmail(checkEmailExist.getEmail());
            otpEntity.setCounter(currentCounter);

            otpMapper.saveOTP(otpEntity);
            System.out.println(otp);
            sendEmail(request.getEmail(), "OTP ", "Your OTP is: " + otp + " will expire in 30s");
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setData("Send email Success");
            return baseResponse;
        }
    }

    @Override
    public BaseResponse resetPassword(UserRequest request) {
        BaseResponse baseResponse = new BaseResponse();
//        System.out.println(validateOtp(request.getOtp(),request.getEmail()));

        if(validateOtp(request.getOtp(), request.getEmail())){
            if(request.getPassword().equals(request.getRePassword())){
                String hashPassword = passwordEncoder.encode(request.getPassword());
                request.setPassword(hashPassword);
                Integer reset = userMapper.resetPassword(request);
                baseResponse.setData(reset);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setErrorDesc("Reset password thành công");
            }
            else {
                baseResponse.setErrorCode(HttpStatus.NOT_ACCEPTABLE.name());
                baseResponse.setErrorDesc("Password không trùng khớp");
            }

        }else{
            baseResponse.setErrorDesc("Sai mã OTP hoặc hết hạn");
            baseResponse.setErrorCode(HttpStatus.UNAUTHORIZED.name());
            return baseResponse;
        }
        return baseResponse;
    }

    public static String generateOTP() throws NoSuchAlgorithmException {
        byte[] secretKeyBytes = SECRET_KEY.getBytes();
        SecureRandom random = new SecureRandom();
        long counter = System.currentTimeMillis() / 30000; // Mỗi 30 giây tăng một lần

        byte[] counterBytes = new byte[8];
        for (int i = 7; i >= 0; i--) {
            counterBytes[i] = (byte) counter;
            counter >>= 8;
        }

        byte[] data = new byte[secretKeyBytes.length + counterBytes.length];
        System.arraycopy(secretKeyBytes, 0, data, 0, secretKeyBytes.length);
        System.arraycopy(counterBytes, 0, data, secretKeyBytes.length, counterBytes.length);

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(data);

        // Chỉ sử dụng 4 byte cuối cùng của hash
        int offset = hash[hash.length - 1] & 0xF;
        int binary = ((hash[offset] & 0x7F) << 24) | ((hash[offset + 1] & 0xFF) << 16) | ((hash[offset + 2] & 0xFF) << 8) | (hash[offset + 3] & 0xFF);

        // Chuyển đổi thành chuỗi 6 số
        int otp = binary % 1_000_000;
        return String.format("%06d", otp);
    }

    private boolean validateOtp(String otp, String email) {
        LocalDateTime now = LocalDateTime.now();
        OTP otpEntity = otpMapper.validateOTP(otp, email);

        if(otpEntity!=null){
            Duration duration = Duration.between(otpEntity.getExpiryTime(), now);

            double seconds = (double) duration.toMillis() / 1000.0;

//        System.out.println("Seconds: " + seconds);
            if (seconds<60) {
                // Calculate the current 30-second interval
                return true;
            }
        }
        return false;
    }
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
