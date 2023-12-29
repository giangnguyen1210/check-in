package com.pos.service;


import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.UserRequest;
import com.pos.dto.response.BaseResponse;

public interface CheckinCheckoutService {

    BaseResponse getListCheckin(CheckInCheckOutRequest request);

    BaseResponse checkOut(CheckInCheckOutRequest request);

    BaseResponse checkIn(CheckInCheckOutRequest request);
}
