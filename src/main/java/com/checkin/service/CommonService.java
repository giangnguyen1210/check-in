package com.checkin.service;

import com.checkin.dto.request.*;
import com.checkin.dto.response.BaseResponse;

public interface CommonService {
    BaseResponse listRole();
    BaseResponse listDepartment();
    BaseResponse listStatus();
    BaseResponse listUnit();
    BaseResponse listPosition();
    BaseResponse listJobTitle();
    BaseResponse listGender();
    BaseResponse listTypeOfQuestion();

    BaseResponse createGender(GenderRequest genderRequest);

    BaseResponse createJobTitle(JobTitleRequest request);

    BaseResponse createStatus(StatusRequest request);

    BaseResponse createUnit(UnitRequest request);

    BaseResponse createPosition(PositionRequest request);

    BaseResponse createRole(RoleRequest request);

    BaseResponse createDepartment(DepartmentRequest request);
    BaseResponse createTypeOfQuestion(TypeOfQuestionRequest request);

    BaseResponse getListObject();
}
