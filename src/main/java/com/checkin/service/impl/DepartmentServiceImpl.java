package com.checkin.service.impl;

import com.checkin.common.SupportService;
import com.checkin.dto.request.*;
import com.checkin.dto.response.*;
import com.checkin.mapper.*;
import com.checkin.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private SupportService service;

    @Override
    public BaseResponse listDepartment(DepartmentRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<DepartmentResponse> departments = departmentMapper.listDepartment(request);
        baseResponse.setData(departments);
        baseResponse.setTotalRecords(departments.size());
        return baseResponse;
    }
    @Override
    public BaseResponse createDepartment(DepartmentRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer departmentCheckExist = departmentMapper.checkDepartmentExist(request);
        System.out.println(departmentCheckExist);
        if(departmentCheckExist==0){
            String id = "DEPARTMENT-";
            int getNextId = 0;
            if(departmentMapper.totalDepartment()>0){
                DepartmentResponse department = departmentMapper.getNextCode();
                getNextId = Integer.parseInt(department.getCode().substring(department.getCode().length() - 4))+1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());

            if(request!=null && !request.getName().equals("") && request.getBranch()!=null){
                departmentMapper.createDepartment(request);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setErrorDesc("Tạo mới phòng ban thành công");
            }else{
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Tạo mới thất bại, Tên phòng ban hoặc chi nhánh không được để trống");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Tạo mới thất bại, Phòng ban đã tồn tại");
        }
        return baseResponse;
    }
    @Override
    public BaseResponse updateDepartment(DepartmentRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getName()==null){
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(), "Chỉnh sửa thất bại, Tên phòng ban không được trống");
        }
        if(request.getBranch()==null){
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(), "Chỉnh sửa thất bại, Tên chi nhánh không được trống");

        }

        Integer b = departmentMapper.updateDepartment(request);
        if (b !=0) {
            baseResponse.setData(request);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setTotalRecords(departmentMapper.totalDepartment());
            baseResponse.setErrorDesc("Chỉnh sửa thành công");
        } else {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Chỉnh sửa thất bại");
            return baseResponse;
        }
        return baseResponse;
    }
}
