package com.checkin.service.impl;

import com.checkin.common.SupportService;
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
    @Autowired
    private SupportService service;
    @Override
    public BaseResponse createUser(UserRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        String defaultPassword = "123456";
        String hashedPassword = passwordEncoder.encode(defaultPassword);
        request.setPassword(hashedPassword);
        UserResponse checkEmailExist = userMapper.findByEmail(request.getEmail());
        if(request.getFullname()==null || request.getFullname().isEmpty()){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Họ và tên không được để trống");
            return baseResponse;
        }else if(request.getDepartmentCode() == null || request.getDepartmentCode().isEmpty()){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Phòng ban không được để trống");
            return baseResponse;
        }else if(request.getUnitCode() == null|| request.getUnitCode().isEmpty()){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Đơn vị không được để trống");
            return baseResponse;
        }else if(request.getJobTitleCode() == null || request.getJobTitleCode().isEmpty()){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Chức danh không được để trống");
            return baseResponse;
        }else if(request.getPositionCode() == null || request.getPositionCode().isEmpty()){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Vị trí không được để trống");
            return baseResponse;
        }
        if(checkEmailExist!=null){
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Email đã tồn tại");
            return baseResponse;
        }
        else{
            String id = "EMPLOYEE-";
            int getNextId = 0;
            if(userMapper.totalUser()>0 || userMapper.totalUser()==null) {
                UserResponse userResponse = userMapper.getNextCode();
                getNextId = Integer.parseInt(userResponse.getEmployeeCode().substring(userResponse.getEmployeeCode().length() - 4)) + 1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setEmployeeCode((id+pad).trim());
            System.out.println("employeecode: "+request.getEmployeeCode());
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
        baseResponse.setTotalRecords(userMapper.countUser(request));
        baseResponse.setErrorCode(HttpStatus.OK.name());
        baseResponse.setData(list);
        return baseResponse;
    }

    @Override
    public BaseResponse updateUser(UserRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if (request == null || Strings.isNullOrEmpty(request.getFullname())
                || Strings.isNullOrEmpty(request.getEmail())) {
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(),
                    "Tên hoặc email không được bỏ trống");
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
    public BaseResponse deActivateUser(UserRequest request){
        Integer deactive = userMapper.deActivateUser(request);
        if(deactive==0){
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(), "Không thành công, Bạn phải chọn dữ liệu để huỷ kích hoạt");
        }
        return new BaseResponse(deactive, HttpStatus.OK.name(), "Huỷ kích hoạt thành công");

    }
    @Override
    public BaseResponse activateUser(UserRequest request){
        Integer deactive = userMapper.activateUser(request);
        if(deactive==0){
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(), "Không thành công, Bạn phải chọn dữ liệu để kích hoạt");
        }
        return new BaseResponse(deactive, HttpStatus.OK.name(), "Kích hoạt hành công");

    }
}
