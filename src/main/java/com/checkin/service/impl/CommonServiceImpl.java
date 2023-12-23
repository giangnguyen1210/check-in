package com.checkin.service.impl;

import com.checkin.common.SupportService;
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
    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private TypeOfQuestionMapper toqMapper;

    @Autowired
    private SupportService service;
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
        if(role==null || role!=1){
            if(request!=null && !request.getName().equals("")){
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
        Integer departmentCheckExist = departmentMapper.checkDepartmentExist(request);
        System.out.println(departmentCheckExist);
        if(departmentCheckExist==0){
            String id = "DEPARTMENT-";
            int getNextId = 0;
            if(departmentMapper.listDepartment().size()>0){
                DepartmentResponse department = departmentMapper.getNextCode();
                getNextId = Integer.parseInt(department.getCode().substring(department.getCode().length() - 4))+1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());

            if(request!=null && !request.getName().equals("")){
                departmentMapper.createDepartment(request);
                baseResponse.setErrorCode(HttpStatus.CREATED.name());
                baseResponse.setErrorDesc("Create success");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail, Department existed!");
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
        Integer status = statusMapper.checkStatusExist(request);
        if(status==null || status!=1){
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
        Integer unitCheckExist = unitMapper.checkUnitExist(request);
        if(unitCheckExist==null || unitCheckExist!=1){
            String id = "UNIT-";
            int getNextId = 0;
            if(unitMapper.listUnit().size()>0){
                UnitResponse unit = unitMapper.getNextCode();
                getNextId = Integer.parseInt(unit.getCode().substring(unit.getCode().length() - 4))+1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());

            if(request!=null && !request.getName().equals("")){
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
        Integer positionCheckExist = positionMapper.checkPositionExist(request);
        if(positionCheckExist == null || positionCheckExist!=1){
            String id = "POSITION-";
            int getNextId = 0;
            if(positionMapper.listPosition().size()>0){
                PositionResponse position = positionMapper.getNextCode();
                getNextId = Integer.parseInt(position.getCode().substring(position.getCode().length() - 4))+1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());


            if(request!=null && !request.getName().equals("")){
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
        if(jobTitle==null || jobTitle!=1){
            String id = "JOB-";
            int getNextId = 0;
            if(jobTitleMapper.listJobTitle().size()>0){
                JobTitleResponse jobTitleResponse = jobTitleMapper.getNextCode();
                getNextId = Integer.parseInt(jobTitleResponse.getCode().substring(jobTitleResponse.getCode().length() - 4))+1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());

            if(request!=null && !request.getName().equals("")){
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
        if(gender==null || gender!=1){
            genderMapper.createGender(request);
            baseResponse.setErrorCode(HttpStatus.CREATED.name());
            baseResponse.setErrorDesc("Create success");
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse listTypeOfQuestion() {
        BaseResponse baseResponse = new BaseResponse();
        List<TypeOfQuestionResponse> toqs = toqMapper.listTypeOfQuestion();
        baseResponse.setData(toqs);
        baseResponse.setTotalRecords(toqs.size());
        return baseResponse;
    }

    @Override
    public BaseResponse createTypeOfQuestion(TypeOfQuestionRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        toqMapper.createTypeOfQuestion(request);
        baseResponse.setErrorCode(HttpStatus.CREATED.name());
        baseResponse.setErrorDesc("Create success");
        return baseResponse;
    }

    @Override
    public BaseResponse getListObject() {
        BaseResponse baseResponse = new BaseResponse();
        List<ObjectResponse> object = commonMapper.getListObject();
        baseResponse.setData(object);
        baseResponse.setTotalRecords(object.size());
        return baseResponse;
    }
}
