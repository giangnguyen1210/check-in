package com.checkin.controller;

import com.checkin.dto.request.*;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/common/")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @PostMapping(value = "/list-role")
    public ResponseEntity<BaseResponse> getListRole(){
        return new ResponseEntity<>(commonService.listRole(), HttpStatus.OK);
    }

    @PostMapping(value = "/create-role")
    public ResponseEntity<BaseResponse> createRole(@RequestBody RoleRequest request){
        return new ResponseEntity<>(commonService.createRole(request), HttpStatus.OK);
    }
    @PostMapping(value = "/list-status")
    public ResponseEntity<BaseResponse> getListStatus(){
        return new ResponseEntity<>(commonService.listStatus(), HttpStatus.OK);
    }

    @PostMapping(value = "/create-status")
    public ResponseEntity<BaseResponse> createStatus(@RequestBody StatusRequest request){
        return new ResponseEntity<>(commonService.createStatus(request), HttpStatus.OK);
    }
    @PostMapping(value = "/list-department")
    public ResponseEntity<BaseResponse> getListDepartment(){
        return new ResponseEntity<>(commonService.listDepartment(), HttpStatus.OK);
    }
    @PostMapping(value = "/create-department")
    public ResponseEntity<BaseResponse> createRole(@RequestBody DepartmentRequest request){
        return new ResponseEntity<>(commonService.createDepartment(request), HttpStatus.OK);
    }
    @PostMapping(value = "/list-unit")
    public ResponseEntity<BaseResponse> getListUnit(){
        return new ResponseEntity<>(commonService.listUnit(), HttpStatus.OK);
    }

    @PostMapping(value = "/create-unit")
    public ResponseEntity<BaseResponse> createUnit(@RequestBody UnitRequest request){
        return new ResponseEntity<>(commonService.createUnit(request), HttpStatus.OK);
    }
    @PostMapping(value = "/list-gender")
    public ResponseEntity<BaseResponse> getListGender(){
        return new ResponseEntity<>(commonService.listGender(), HttpStatus.OK);
    }
    @PostMapping(value = "/create-gender")
    public ResponseEntity<BaseResponse> createGender(@RequestBody GenderRequest request){
        return new ResponseEntity<>(commonService.createGender(request), HttpStatus.OK);
    }
    @PostMapping(value = "/list-position")
    public ResponseEntity<BaseResponse> getListPosition(){
        return new ResponseEntity<>(commonService.listPosition(), HttpStatus.OK);
    }

    @PostMapping(value = "/create-position")
    public ResponseEntity<BaseResponse> createPosition(@RequestBody PositionRequest request){
        return new ResponseEntity<>(commonService.createPosition(request), HttpStatus.OK);
    }

    @PostMapping(value = "/list-jobtitle")
    public ResponseEntity<BaseResponse> getListJobTitle(){
        return new ResponseEntity<>(commonService.listJobTitle(), HttpStatus.OK);
    }

    @PostMapping(value = "/create-jobtitle")
    public ResponseEntity<BaseResponse> createJobTitle(@RequestBody JobTitleRequest request){
        return new ResponseEntity<>(commonService.createJobTitle(request), HttpStatus.OK);
    }

    @PostMapping(value = "/list-toq")
    public ResponseEntity<BaseResponse> getListTypeOfQuestion(){
        return new ResponseEntity<>(commonService.listTypeOfQuestion(), HttpStatus.OK);
    }

    @PostMapping(value = "/create-toq")
    public ResponseEntity<BaseResponse> createTypeOfQuestion(@RequestBody TypeOfQuestionRequest request){
        return new ResponseEntity<>(commonService.createTypeOfQuestion(request), HttpStatus.OK);
    }

    @PostMapping(value = "/list-objects")
    public ResponseEntity<BaseResponse> getListObject(){
        return new ResponseEntity<>(commonService.getListObject(), HttpStatus.OK);
    }
}
