package com.pos.service;


import com.pos.dto.request.UserRequest;
import com.pos.dto.response.BaseResponse;

public interface UserService {
//    BaseResponse createUser(UserRequest request);

    BaseResponse getListUser(UserRequest request);

    BaseResponse updateUser(UserRequest response);

    BaseResponse deActivateUser(UserRequest request);
}
