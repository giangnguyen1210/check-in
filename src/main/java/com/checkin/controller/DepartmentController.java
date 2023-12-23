package com.checkin.controller;

import com.checkin.dto.request.DepartmentRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping(value = "/list-department")
    public ResponseEntity<BaseResponse> getListDepartment(@RequestBody DepartmentRequest request){
        return new ResponseEntity<>(departmentService.listDepartment(request), HttpStatus.OK);
    }
    @PostMapping(value = "/create-department")
    public ResponseEntity<BaseResponse> createDepartment(@RequestBody DepartmentRequest request){
        return new ResponseEntity<>(departmentService.createDepartment(request), HttpStatus.OK);
    }
    @PostMapping(value = "/update-department")
    public ResponseEntity<BaseResponse> updateDepartment(@RequestBody DepartmentRequest request){
        return new ResponseEntity<>(departmentService.updateDepartment(request), HttpStatus.OK);
    }
}
