package com.checkin.service.impl;

import com.checkin.common.SupportService;
import com.checkin.dto.request.DepartmentRequest;
import com.checkin.dto.request.OpinionRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.DepartmentResponse;
import com.checkin.dto.response.OpinionResponse;
import com.checkin.mapper.DepartmentMapper;
import com.checkin.mapper.OpinionMapper;
import com.checkin.service.DepartmentService;
import com.checkin.service.OpinionService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {
    @Autowired
    private OpinionMapper opinionMapper;


    @Autowired
    private SupportService service;

    @Override
    public BaseResponse listOpinion(OpinionRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<OpinionResponse> opinion = opinionMapper.listOpinion(request);
        baseResponse.setData(opinion);
        baseResponse.setTotalRecords(opinion.size());
        return baseResponse;
    }

    @Override
    public BaseResponse responseOpinion(OpinionRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if (request.getResponse() ==null||request.getResponse().equals("")) {
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(),
                    "Nội dung phản hồi không được bỏ trống");
        }
        int b = opinionMapper.responseOpinion(request);
        if (b !=0) {
            baseResponse.setData(request);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setErrorDesc("Gửi phản hồi thành công");
        } else {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Gửi phản hồi thất bại");
            return baseResponse;
        }
        return baseResponse;
    }


}
