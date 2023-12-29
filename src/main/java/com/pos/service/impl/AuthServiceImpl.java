package com.pos.service.impl;

import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.UserRequest;
import com.pos.dto.response.AuthResponse;
import com.pos.dto.response.BaseResponse;
import com.pos.dto.response.UserResponse;
import com.pos.mapper.CheckInCheckOutMapper;
import com.pos.mapper.HistoryLoginMapper;
import com.pos.mapper.UserMapper;
import com.pos.security.JwtGenerator;
import com.pos.service.AuthService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private static final String SECRET_KEY = "check-in"; // Thay thế bằng khoá bí mật thực tế của bạn



    @Autowired
    private HistoryLoginMapper historyLoginMapper;
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
                request.setEmployeeCode(user.getEmployeeCode());
                historyLoginMapper.createHistoryLogin(request);
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





}
