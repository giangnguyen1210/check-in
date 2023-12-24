package com.checkin.service.impl;

import com.checkin.common.SupportService;
import com.checkin.dto.request.HolidayRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.DepartmentResponse;
import com.checkin.dto.response.HolidayResponse;
import com.checkin.dto.response.SurveyResponse;
import com.checkin.mapper.HolidayMapper;
import com.checkin.mapper.SurveyMapper;
import com.checkin.service.HolidayService;
import com.checkin.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {
    @Autowired
    private SupportService service;

    @Autowired
    private HolidayMapper holidayMapper;
    @Override
    public BaseResponse createHoliday(HolidayRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer holidayCheckExist = holidayMapper.checkHolidayExist(request);
        if(holidayCheckExist==0){
            String id = "HOLIDAY-";
            int getNextId = 0;
            if(holidayMapper.totalHoliday()>0){
                HolidayResponse holiday = holidayMapper.getNextCode();
                getNextId = Integer.parseInt(holiday.getCode().substring(holiday.getCode().length() - 4))+1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());

            if(request!=null && !request.getName().equals("")){
                holidayMapper.createHoliday(request);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setErrorDesc("Create success");
            }else{
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Event Name is null");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail, Event existed!");
        }

        return baseResponse;
    }
    @Override
    public BaseResponse getListHoliday(HolidayRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<HolidayResponse> list = holidayMapper.getListHoliday(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setData(list);
        return baseResponse;
    }

    @Override
    public BaseResponse updateHoliday(HolidayRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer holidayCheckExist = holidayMapper.checkHolidayExist(request);
        if(holidayCheckExist==0){
            if(request!=null && !request.getName().equals("")){
                holidayMapper.updateHoliday(request);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setErrorDesc("Create success");
            }else{
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Event Name is null");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail, Event existed!");
        }

        return baseResponse;
    }
}
