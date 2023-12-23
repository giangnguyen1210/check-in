package com.checkin.service.impl;

import com.checkin.common.SupportService;
import com.checkin.dto.request.SurveyRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.SurveyResponse;
import com.checkin.mapper.SurveyMapper;
import com.checkin.service.SurveyService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SupportService service;

    @Autowired
    private SurveyMapper surveyMapper;
    @Override
    public BaseResponse createSurvey(SurveyRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getName().equals("")||request.getName()==null){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Tên khảo sát không được trống");
        }
        else if(request.getQuestion1()!=null){
            if(request.getTypeOfQuestion1Id()==null){
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Loại câu hỏi 1 không được trống");
            }
        }
        else if(request.getQuestion2()!=null){
            if(request.getTypeOfQuestion2Id()==null){
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Loại câu hỏi 2 không được trống");
            }
        }
        else if(request.getMandatoryObject()==null) {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Đối tượng áp dụng (bắt buộc) không được trống");
        }
        String id = "SURVEY-";
        int getNextId = 0;
        if(surveyMapper.totalSurvey()>0 || surveyMapper.totalSurvey()==null) {
            SurveyResponse response = surveyMapper.getNextCode();
            getNextId = Integer.parseInt(response.getCode().substring(response.getCode().length() - 4)) + 1;
        }
        String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
        request.setCode((id+pad).trim());
        Integer b = surveyMapper.createSurvey(request);
        if (b !=0) {
            baseResponse.setData(request);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setTotalRecords(surveyMapper.totalSurvey());
            baseResponse.setErrorDesc("Thêm mới thành công");
        } else {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Thêm mới thất bại");
            return baseResponse;
        }

        return baseResponse;
    }
    @Override
    public BaseResponse getListSurvey(SurveyRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<SurveyResponse> list = surveyMapper.getListSurvey(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setData(list);
        return baseResponse;
    }

    @Override
    public BaseResponse updateSurvey(SurveyRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getName()==null){
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(), "Tên khảo sát không được trống");
        }
        if(request.getQuestion1()!=null){
            if(request.getTypeOfQuestion1Id()==null){
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Loại câu hỏi không được trống");
                return baseResponse;
            }
        }
        if(request.getQuestion2()!=null){
            if(request.getTypeOfQuestion2Id()==null){
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Loại câu hỏi không được trống");
                return baseResponse;
            }
        }

        Integer b = surveyMapper.updateSurvey(request);
        if (b !=0) {
            baseResponse.setData(request);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setTotalRecords(surveyMapper.totalSurvey());
            baseResponse.setErrorDesc("chỉnh sửa thành công");
        } else {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("chỉnh sửa thất bại");
            return baseResponse;
        }
        return baseResponse;
    }
}
