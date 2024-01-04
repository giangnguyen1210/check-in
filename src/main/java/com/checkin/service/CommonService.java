package com.checkin.service;

import com.checkin.dto.request.*;
import com.checkin.dto.response.BaseResponse;

public interface CommonService {
    BaseResponse listRole();
    BaseResponse listStatus();
    BaseResponse listPosition();
    BaseResponse listJobTitle();
    BaseResponse listGender();
    BaseResponse listTypeOfQuestion();
    BaseResponse listBranch();

    BaseResponse createGender(GenderRequest genderRequest);

    BaseResponse createJobTitle(JobTitleRequest request);

    BaseResponse listUserStatus();

    BaseResponse createStatus(StatusRequest request);

    BaseResponse createPosition(PositionRequest request);

    BaseResponse createRole(RoleRequest request);

    BaseResponse createTypeOfQuestion(TypeOfQuestionRequest request);

    BaseResponse getListObject();

    BaseResponse createBranch(BranchRequest request);
}
