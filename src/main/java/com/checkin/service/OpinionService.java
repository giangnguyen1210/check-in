package com.checkin.service;

import com.checkin.dto.request.DepartmentRequest;
import com.checkin.dto.request.OpinionRequest;
import com.checkin.dto.response.BaseResponse;

public interface OpinionService {

    BaseResponse listOpinion(OpinionRequest request);

    BaseResponse responseOpinion(OpinionRequest request);


}
