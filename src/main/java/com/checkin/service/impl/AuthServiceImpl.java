package com.checkin.service.impl;
import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.*;
import com.checkin.mapper.*;
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
import java.time.*;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    private SettingPasswordMapper passwordMapper;
    @Autowired
    private SettingSessionMapper sessionMapper;
    @Autowired
    private LoginHistoryMapper loginHistoryMapper;
    private static final String SECRET_KEY = "check-in"; // Thay thế bằng khoá bí mật thực tế của bạn

    @Override
    public BaseResponse login(UserRequest request) {
        AuthResponse authResponse = new AuthResponse("");
        UserResponse user = userMapper.findByEmail(request.getEmail());

        if(user==null){
            authResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            authResponse.setErrorDesc("Người dùng đã bị deactive");
            return authResponse;
        }
        request.setEmployeeCode(user.getEmployeeCode());
        UserLoginResponse userLoginResponse = userMapper.getUserLoginHistory(user.getEmployeeCode(), user.getEmail());
        if(userLoginResponse!=null &&userLoginResponse.getNumberWrongPassword()>=sessionMapper.getSetting().getMaxWrongTime()) {
            Date dbDate = userLoginResponse.getDate();
            LocalTime dbTime = userLoginResponse.getTime();

            // Tạo đối tượng LocalDateTime từ date và time
            LocalDateTime dbDateTime = dbDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            // Tạo đối tượng LocalDateTime từ date và time
            LocalDateTime dbDateTimeWithTime = LocalDateTime.of(dbDateTime.toLocalDate(), dbTime);

            // Thời gian hiện tại
            LocalDateTime now = LocalDateTime.now();

            // Tính chênh lệch thời gian theo phút
            long minutesDifference = ChronoUnit.MINUTES.between(dbDateTimeWithTime, now);

            long timeLoginAgain = sessionMapper.getSetting().getTimeLoginAgain();
            long result = timeLoginAgain - minutesDifference;
            if(result>0){
                authResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                authResponse.setErrorDesc("Tài khoản của bạn đang bị khoá, vui lòng đăng nhập lại sau "+result + " phút");
                return authResponse;
            }else{
                request.setEmployeeCode(user.getEmployeeCode());
                userMapper.updateLoginIfRight(request);
            }
        }
        else{
            if(user.getRoleId()!=1){
                authResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                authResponse.setErrorDesc("Bạn không có quyền truy cập");
                return authResponse;
            }
            if(request.getEmail()==null || request.getPassword()==null){
                authResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                authResponse.setErrorCode("Email hoặc mật khẩu không được để trống");
            }else{
                if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String token = jwtGenerator.generateToken(authentication);
                    Claims claims = JwtGenerator.getEmailFromJwt(token);

                    // Lấy thông tin người dùng
                    String username = claims.getSubject();

                    System.out.println("max time: "+sessionMapper.getSetting().getMaxTime());
                    if(loginHistoryMapper.countHistoryLogin(request)>sessionMapper.getSetting().getMaxTime()){
                        authResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                        authResponse.setErrorDesc("Bạn đã đăng nhập số quá số lần trong ngày là "+sessionMapper.getSetting().getMaxTime());
                    }else{

                        loginHistoryMapper.createHistoryLogin(request);
                        authResponse.setErrorDesc("Đăng nhập thành công");
                        authResponse.setAccessToken(token);
                        authResponse.setData(username);
                        authResponse.setErrorCode(HttpStatus.OK.name());
                    }

                }else{
                    authResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                    authResponse.setErrorDesc("Mật khẩu không trùng khớp");

                    System.out.println("login response: "+userLoginResponse);
                    if(userLoginResponse==null){
                        userMapper.insertLoginHistory(user.getEmployeeCode(), user.getEmail());
                        return authResponse;
                    }
                    System.out.println("getMaxWrongTime: "+userLoginResponse.getNumberWrongPassword());
                    if(userLoginResponse.getNumberWrongPassword()<sessionMapper.getSetting().getMaxWrongTime()){
                        request.setNumberWrongPassword(userLoginResponse.getNumberWrongPassword()+1);
                        request.setEmployeeCode(userLoginResponse.getEmployeeCode());
                        userMapper.updateLoginHistory(request);
                    }else{
                        authResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                        authResponse.setErrorDesc("Tài khoản của bạn đã đăng nhập sai trên"+ userLoginResponse.getNumberWrongPassword()+" lần"+", tài khoản của bạn sẽ bị khoá trong "+sessionMapper.getSetting().getTimeLoginAgain()+ " phút");
                    }
                }
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
            baseResponse.setErrorDesc("Email không tồn tại");
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
            baseResponse.setData("Gửi email thành công!, mời bạn nhập mã OTP");
            return baseResponse;
        }
    }

    @Override
    public BaseResponse resetPassword(UserRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        SettingPasswordResponse response = passwordMapper.getSetting();
        System.out.println("response auth: "+response);
        UserResponse userResponse = userMapper.findByEmail(request.getEmail());

        if(validateOtp(request.getOtp(), request.getEmail())){
            if(request.getPassword().length()<response.getMinLength()){
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Độ dài tối thiểu của mật khẩu là "+response.getMinLength());
                return baseResponse;
            }else if(request.getPassword().length()>response.getMaxLength()){
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Độ dài tối đa của mật khẩu là "+response.getMaxLength());
                return baseResponse;
            }
            else if(response.getMinChar()>countChar(request.getPassword())){
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Số lượng chữ cái của mật khẩu là "+response.getMinChar());
                return baseResponse;
            } else if (response.getMinNumber()>countNumber(request.getPassword())) {
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Số lượng chữ số của mật khẩu là "+response.getMinNumber());
                return baseResponse;
            } else if (response.getMinSpecialChar()>countSpecialChar(request.getPassword())) {
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Số lượng ký tự đặc biệt của mật khẩu là "+response.getMinSpecialChar());
                return baseResponse;
            }
            if(request.getPassword().equals(request.getRePassword())){
                String hashPassword = passwordEncoder.encode(request.getPassword());
                request.setPassword(hashPassword);
                Integer reset = userMapper.resetPassword(request);
                userMapper.insertTimeResetPassword(userResponse.getEmployeeCode());
                baseResponse.setData(reset);
                baseResponse.setErrorCode(HttpStatus.OK.name());
//                userMapper.insertChangePasswordHistory(request);
                baseResponse.setErrorDesc("Đặt lại mật khẩu thành công");
            }
            else {
                baseResponse.setErrorCode(HttpStatus.NOT_ACCEPTABLE.name());
                baseResponse.setErrorDesc("Mật khẩu không trùng khớp");
            }
        }else{
            baseResponse.setErrorDesc("Sai mã OTP hoặc hết hạn");
            baseResponse.setErrorCode(HttpStatus.UNAUTHORIZED.name());
        }
        return baseResponse;
    }

    public int countNumber(String str) {
        // Sử dụng biểu thức chính quy để tìm tất cả các chữ số
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(str);

        int numberNum = 0;

        // Duyệt qua mọi ký tự và kiểm tra
        while (matcher.find()) {
            numberNum++;
        }

        return numberNum;
    }

    public int countChar(String str) {
        // Sử dụng biểu thức chính quy để tìm tất cả các chữ cái
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(str);

        int numberChar = 0;

        // Duyệt qua mọi ký tự và kiểm tra
        while (matcher.find()) {
            numberChar++;
        }

        return numberChar;
    }

    public static int countSpecialChar(String str) {
        // Sử dụng biểu thức chính quy để tìm tất cả các ký tự đặc biệt
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        Matcher matcher = pattern.matcher(str);

        int numberSpecialChar = 0;

        // Duyệt qua mọi ký tự và kiểm tra
        while (matcher.find()) {
            numberSpecialChar++;
        }

        return numberSpecialChar;
    }
    public static String generateOTP() throws NoSuchAlgorithmException {
        byte[] secretKeyBytes = SECRET_KEY.getBytes();
        SecureRandom random = new SecureRandom();
        long counter = System.currentTimeMillis() / 120000; // Mỗi 30 giây tăng một lần

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
