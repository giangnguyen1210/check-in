package com.checkin.controller;

import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody UserRequest request){
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping("/send-email-repass")
    public ResponseEntity<BaseResponse> sendEmail(@RequestBody UserRequest request) throws NoSuchAlgorithmException, InvalidKeyException {
        return new ResponseEntity<>(authService.sendEmailOTP(request), HttpStatus.OK);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<BaseResponse> resetPassword(@RequestBody UserRequest request) throws NoSuchAlgorithmException, InvalidKeyException {
        return new ResponseEntity<>(authService.resetPassword(request), HttpStatus.OK);
    }
}
