package com.checkin.service.impl;

import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.UserResponse;
import com.checkin.mapper.UserMapper;
import com.checkin.service.UserService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;
    @Override
    public BaseResponse createUser(UserRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        String defaultPassword = "123456";
        String hashedPassword = passwordEncoder.encode(defaultPassword);
        request.setPassword(hashedPassword);
        UserResponse checkEmailExist = userMapper.findByEmail(request.getEmail());
        if(checkEmailExist!=null){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Email is existed");
            return baseResponse;
        }
        if(request.getFullname()==null){
            baseResponse.setErrorCode("Full name is required");
            baseResponse.setErrorDesc(HttpStatus.BAD_REQUEST.name());
            return baseResponse;
        }else if(request.getDepartmentId() == null){
            baseResponse.setErrorCode("Department is required ");
            baseResponse.setErrorDesc(HttpStatus.BAD_REQUEST.name());
            return baseResponse;
        }else if(request.getUnitId() == null){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Unit is required ");
            return baseResponse;
        }else if(request.getJobTitleId() == null){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Job title is required ");
            return baseResponse;
        }else if(request.getPositionId() == null){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Position is required ");
            return baseResponse;
        }else{
            Integer b = userMapper.createUser(request);
            if (b !=0) {
                baseResponse.setData(request);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setTotalRecords(userMapper.totalUser());
                baseResponse.setErrorDesc("Thêm mới thành công");
            } else {
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Thêm mới thất bại");
                return baseResponse;
            }
        }
        return baseResponse;
    }

    @Override
    public BaseResponse getListUser(UserRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<UserResponse> list = userMapper.getListUser(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setData(list);
        return baseResponse;
    }

    @Override
    public BaseResponse updateUser(UserRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if (request == null || Strings.isNullOrEmpty(request.getFullname())
                || Strings.isNullOrEmpty(request.getEmail())) {
            return new BaseResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "Fields is required");
        }
        int b = userMapper.updateUser(request);
        if (b !=0) {
            baseResponse.setData(request);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setTotalRecords(userMapper.totalUser());
            baseResponse.setErrorDesc("chỉnh sửa người dùng thành công");
        } else {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("chỉnh sửa người dùng thất bại");
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteUser(UserRequest request) {
        return null;
    }

    @Override
    public BaseResponse userDetail(UserRequest request) {
        return null;
    }
}
