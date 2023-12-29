package com.pos.service.impl;

import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.response.BaseResponse;
import com.pos.dto.response.CheckInCheckOutResponse;
import com.pos.mapper.CheckInCheckOutMapper;
import com.pos.mapper.UserMapper;
import com.pos.service.CheckinCheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckinCheckoutServiceImpl implements CheckinCheckoutService {
    @Autowired
    private CheckInCheckOutMapper checkInCheckOutMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public BaseResponse getListCheckin(CheckInCheckOutRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<CheckInCheckOutResponse> checkin = checkInCheckOutMapper.listHistoryCheckin(request);
        baseResponse.setData(checkin);
        baseResponse.setErrorCode(HttpStatus.OK.name());
        baseResponse.setErrorDesc("OK");
        return baseResponse;
    }
    @Override
    public BaseResponse checkOut(CheckInCheckOutRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(userMapper.checkUserExist(request)==0){
            baseResponse.setErrorCode(HttpStatus.NOT_FOUND.name());
            baseResponse.setErrorDesc("Người dùng không tồn tại");
            return baseResponse;
        }
        System.out.println("dòng 79: x"+checkInCheckOutMapper.checkCheckInExist(request));
        if(checkInCheckOutMapper.checkCheckInExist(request)==0){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Bạn chưa checkin");
            return baseResponse;
        }
        baseResponse.setErrorDesc("Checkout thành công");
        baseResponse.setErrorCode(HttpStatus.OK.name());
        baseResponse.setData(checkInCheckOutMapper.checkOut(request));
        return baseResponse;
    }

    @Override
    public BaseResponse checkIn(CheckInCheckOutRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(userMapper.checkUserExist(request)==0){
            baseResponse.setErrorCode(HttpStatus.NOT_FOUND.name());
            baseResponse.setErrorDesc("Người dùng không tồn tại");
            return baseResponse;
        }
        if(checkInCheckOutMapper.checkCheckoutExist(request)>0){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Bạn đã checkin hôm nay và chưa checkout");
            return baseResponse;
        }
        checkInCheckOutMapper.checkIn(request);
        baseResponse.setErrorDesc("checkin thành công");
        baseResponse.setErrorCode(HttpStatus.OK.name());
        return baseResponse;
    }
}
