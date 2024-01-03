package com.checkin.service.impl;

import com.checkin.dto.request.SettingPasswordRequest;
import com.checkin.dto.request.SettingSessionRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.SettingPasswordResponse;
import com.checkin.dto.response.SettingSessionResponse;
import com.checkin.mapper.SettingPasswordMapper;
import com.checkin.mapper.SettingSessionMapper;
import com.checkin.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingPasswordMapper mapperPassword;

    @Autowired
    private SettingSessionMapper mapperSession;
    @Override
    public BaseResponse insertSettingPassword(SettingPasswordRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getMinLength()==null||
            request.getMaxLength()==null||
            request.getMinChar()==null||
            request.getMinNumber()==null||
            request.getTimeExpire()==null||
            request.getMinSpecialChar()==null
        ){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Không được để trống");
            return baseResponse;
        }
        else{
            mapperPassword.insertSetting(request);
            baseResponse.setErrorDesc("Cập nhật thành công");
            baseResponse.setErrorCode(HttpStatus.OK.name());
            return baseResponse;
        }
    }

    @Override
    public BaseResponse updateSettingPassword(SettingPasswordRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getMinLength()==null||
                request.getMaxLength()==null||
                request.getMinChar()==null||
                request.getMinNumber()==null||
                request.getTimeExpire()==null||
                request.getMinSpecialChar()==null
        ){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Không được để trống");
        }
        else{
            mapperPassword.updateSetting(request);
            mapperPassword.insertSetting(request);
            baseResponse.setErrorDesc("Cập nhật thành công");
            baseResponse.setErrorCode(HttpStatus.OK.name());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse getSettingPassword(SettingPasswordRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            SettingPasswordResponse response =  mapperPassword.getSetting();
            System.out.println("response: "+response);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setErrorDesc("OK");
            baseResponse.setData(response);
            return baseResponse;
        }catch (Exception e){
            return baseResponse;
        }
    }

    @Override
    public BaseResponse insertSettingSession(SettingSessionRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getIntervalTime()==null||
                request.getMaxTime()==null||
                request.getMaxWrongTime()==null||
                request.getTimeLoginAgain()==null
        ){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Không được để trống");
        }
        else{
            mapperSession.insertSetting(request);
            baseResponse.setErrorDesc("Cập nhật thành công");
            baseResponse.setErrorCode(HttpStatus.OK.name());
        } return baseResponse;
    }

    @Override
    public BaseResponse updateSettingSession(SettingSessionRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getIntervalTime()==null||
                request.getMaxTime()==null||
                request.getMaxWrongTime()==null||
                request.getTimeLoginAgain()==null
        ){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Không được để trống");
        }
        else{
            mapperSession.updateSetting(request);
            mapperSession.insertSetting(request);
            baseResponse.setErrorDesc("Cập nhật thành công");
            baseResponse.setErrorCode(HttpStatus.OK.name());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse getSettingSession(SettingSessionRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            SettingSessionResponse response =  mapperSession.getSetting();
            System.out.println("response: "+response);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setErrorDesc("OK");
            baseResponse.setData(response);
            return baseResponse;
        }catch (Exception e){
            return baseResponse;
        }
    }
}
