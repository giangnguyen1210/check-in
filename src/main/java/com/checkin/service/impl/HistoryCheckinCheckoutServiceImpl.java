package com.checkin.service.impl;

import com.checkin.dto.request.CheckinCheckoutRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.CheckinCheckoutResponse;
import com.checkin.mapper.CheckinCheckoutMapper;
import com.checkin.service.HistoryCheckinCheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryCheckinCheckoutServiceImpl implements HistoryCheckinCheckoutService {
    @Autowired
    private CheckinCheckoutMapper checkinCheckoutMapper;
    @Override
    public BaseResponse getListCheckinCheckout(CheckinCheckoutRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<CheckinCheckoutResponse> list = checkinCheckoutMapper.listCheckinCheckout(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setData(list);
        return baseResponse;
    }
    @Override
    public BaseResponse getListCheckinCheckoutDetail(CheckinCheckoutRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<CheckinCheckoutResponse> list = checkinCheckoutMapper.listCheckinCheckoutDetail(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setData(list);
        return baseResponse;
    }

}
