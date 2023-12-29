package com.checkin.service.impl;

import com.checkin.common.SupportService;
import com.checkin.dto.request.RegisterRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.RegisterResponse;
import com.checkin.mapper.RegisterMapper;
import com.checkin.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private SupportService service;

    @Autowired
    private RegisterMapper registerMapper;
    @Override
    public BaseResponse createRegister(RegisterRequest request) {
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
        String id = "Register-";
        int getNextId = 0;
        if(registerMapper.totalRegister()>0 || registerMapper.totalRegister()==null) {
            RegisterResponse response = registerMapper.getNextCode();
            getNextId = Integer.parseInt(response.getCode().substring(response.getCode().length() - 4)) + 1;
        }
        String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
        request.setCode((id+pad).trim());
        Integer b = registerMapper.createRegister(request);
        if (b !=0) {
            baseResponse.setData(request);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setTotalRecords(registerMapper.totalRegister());
            baseResponse.setErrorDesc("Thêm mới thành công");
        } else {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Thêm mới thất bại");
            return baseResponse;
        }

        return baseResponse;
    }
    @Override
    public BaseResponse getListRegister(RegisterRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<RegisterResponse> list = registerMapper.getListRegister(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setErrorCode(HttpStatus.OK.name());
        baseResponse.setData(list);
        return baseResponse;
    }

    @Override
    public BaseResponse updateRegister(RegisterRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getName()==null){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Tên khảo sát không được trống");
            return baseResponse;
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

        Integer b = registerMapper.updateRegister(request);
        if (b !=0) {
            baseResponse.setData(request);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setTotalRecords(registerMapper.totalRegister());
            baseResponse.setErrorDesc("chỉnh sửa thành công");
        } else {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("chỉnh sửa thất bại");
        }
        return baseResponse;
    }
}
