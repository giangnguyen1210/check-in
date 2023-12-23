package com.checkin.service;

import com.checkin.dto.request.RegisterRequest;
import com.checkin.dto.response.BaseResponse;

public interface RegisterService {
    BaseResponse createRegister(RegisterRequest request);

    BaseResponse getListRegister(RegisterRequest request);
    BaseResponse updateRegister(RegisterRequest request);
}
