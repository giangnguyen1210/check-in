package com.checkin.service.impl;

import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.HistoryLoginResponse;
import com.checkin.mapper.HistoryLoginMapper;
import com.checkin.service.HistoryLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryLoginServiceImpl implements HistoryLoginService {
    @Autowired
    private HistoryLoginMapper historyLoginMapper;
    @Override
    public BaseResponse getListHistoryLogin(HistoryLoginRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<HistoryLoginResponse> list = historyLoginMapper.listHistoryLogin(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setData(list);
        return baseResponse;
    }
    @Override
    public BaseResponse getHistoryDetail(HistoryLoginRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<HistoryLoginResponse> list = historyLoginMapper.listHistoryLoginDetail(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setData(list);
        return baseResponse;
    }
}
