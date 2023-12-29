package com.pos.service;


import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.UserRequest;
import com.pos.dto.response.BaseResponse;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface AuthService {
    BaseResponse login(UserRequest request);
}
