package com.checkin.service.impl;

import com.checkin.dto.request.*;
import com.checkin.dto.response.*;
import com.checkin.mapper.*;
import com.checkin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private StatusMapper statusMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private JobTitleMapper jobTitleMapper;
    @Autowired
    private GenderMapper genderMapper;
    @Autowired
    private UnitMapper unitMapper;
    @Override
    public BaseResponse listRole() {
        BaseResponse baseResponse = new BaseResponse();
        List<RoleResponse> roles = roleMapper.listRole();
        baseResponse.setData(roles);
        baseResponse.setTotalRecords(roles.size());
        return baseResponse;
    }
    @Override
    public BaseResponse createRole(RoleRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer role = roleMapper.checkRoleExist(request);
        if(role!=1){
            if(request!=null && !request.equals("")){
                roleMapper.createRole(request);
                baseResponse.setErrorCode(HttpStatus.CREATED.name());
                baseResponse.setErrorDesc("Create success");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail");
        }
        return baseResponse;
    }
    @Override
    public BaseResponse listDepartment() {
        BaseResponse baseResponse = new BaseResponse();
        List<DepartmentResponse> departments = departmentMapper.listDepartment();
        baseResponse.setData(departments);
        baseResponse.setTotalRecords(departments.size());
        return baseResponse;
    }
    @Override
    public BaseResponse createDepartment(DepartmentRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer department = departmentMapper.checkDepartmentExist(request);
        if(department!=1){
            if(request!=null && !request.equals("")){
                departmentMapper.createDepartment(request);
                baseResponse.setErrorCode(HttpStatus.CREATED.name());
                baseResponse.setErrorDesc("Create success");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse listStatus() {
        BaseResponse baseResponse = new BaseResponse();
        List<StatusResponse> statuses = statusMapper.listStatus();
        baseResponse.setData(statuses);
        baseResponse.setTotalRecords(statuses.size());
        return baseResponse;
    }

    @Override
    public BaseResponse createStatus(StatusRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer jobTitle = statusMapper.checkStatusExist(request);
        if(jobTitle!=1){
            if(request!=null && !request.equals("")){
                statusMapper.createStatus(request);
                baseResponse.setErrorCode(HttpStatus.CREATED.name());
                baseResponse.setErrorDesc("Create success");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail");
        }
        return baseResponse;
    }
    @Override
    public BaseResponse listUnit() {
        BaseResponse baseResponse = new BaseResponse();
        List<UnitResponse> units = unitMapper.listUnit();
        baseResponse.setData(units);
        baseResponse.setTotalRecords(units.size());
        return baseResponse;
    }
    @Override
    public BaseResponse createUnit(UnitRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer unit = unitMapper.checkUnitExist(request);
        if(unit!=1){
            if(request!=null && !request.equals("")){
                unitMapper.createUnit(request);
                baseResponse.setErrorCode(HttpStatus.CREATED.name());
                baseResponse.setErrorDesc("Create success");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse listPosition() {
        BaseResponse baseResponse = new BaseResponse();
        List<PositionResponse> positions = positionMapper.listPosition();
        baseResponse.setData(positions);
        baseResponse.setTotalRecords(positions.size());
        return baseResponse;
    }

    @Override
    public BaseResponse createPosition(PositionRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer position = positionMapper.checkPositionExist(request);
        if(position!=1){
            if(request!=null && !request.equals("")){
                positionMapper.createPosition(request);
                baseResponse.setErrorCode(HttpStatus.CREATED.name());
                baseResponse.setErrorDesc("Create success");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail");
        }
        return baseResponse;
    }
    @Override
    public BaseResponse listJobTitle() {
        BaseResponse baseResponse = new BaseResponse();
        List<JobTitleResponse> jobTitles = jobTitleMapper.listJobTitle();
        baseResponse.setData(jobTitles);
        baseResponse.setTotalRecords(jobTitles.size());
        return baseResponse;
    }
    @Override
    public BaseResponse createJobTitle(JobTitleRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer jobTitle = jobTitleMapper.checkJobTitleExist(request);
        if(jobTitle!=1){
            if(request!=null && !request.equals("")){
                jobTitleMapper.createJobTitle(request);
                baseResponse.setErrorCode(HttpStatus.CREATED.name());
                baseResponse.setErrorDesc("Create success");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse listGender() {
        BaseResponse baseResponse = new BaseResponse();
        List<GenderResponse> genders = genderMapper.listGender();
        baseResponse.setData(genders);
        baseResponse.setTotalRecords(genders.size());
        return baseResponse;
    }

    @Override
    public BaseResponse createGender(GenderRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer gender = genderMapper.checkGenderExist(request);
        if(gender!=1){
            genderMapper.createGender(request);
            baseResponse.setErrorCode(HttpStatus.CREATED.name());
            baseResponse.setErrorDesc("Create success");
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail");
        }
        return baseResponse;
    }
}
