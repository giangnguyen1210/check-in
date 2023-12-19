package com.checkin.service;

import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.UserResponse;

public interface UserService {
    BaseResponse createUser(UserRequest request);

    BaseResponse getListUser(UserRequest request);

    BaseResponse updateUser(UserRequest response);

    BaseResponse deleteUser(UserRequest request);

    BaseResponse userDetail(UserRequest request);
}
