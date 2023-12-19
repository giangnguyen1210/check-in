package com.checkin.service;

import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.mapper.UserMapper;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface AuthService {
    BaseResponse login(UserRequest request);
    BaseResponse sendEmailOTP(UserRequest request) throws NoSuchAlgorithmException, InvalidKeyException;

    BaseResponse resetPassword(UserRequest request) throws NoSuchAlgorithmException, InvalidKeyException;
}
