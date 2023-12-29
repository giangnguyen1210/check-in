package com.pos.controller;

import com.pos.dto.request.UserRequest;
import com.pos.dto.response.BaseResponse;
import com.pos.service.AuthService;
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

}
