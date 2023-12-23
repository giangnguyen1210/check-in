package com.checkin.service;

import com.checkin.dto.request.*;
import com.checkin.dto.response.BaseResponse;

public interface DepartmentService {

    BaseResponse listDepartment(DepartmentRequest request);


    BaseResponse createDepartment(DepartmentRequest request);

    BaseResponse updateDepartment(DepartmentRequest request);

//    BaseResponse getListObject();
}
