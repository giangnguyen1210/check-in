package com.checkin.service;

import com.checkin.dto.request.SurveyRequest;
import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.BaseResponse;

public interface SurveyService {
    BaseResponse createSurvey(SurveyRequest request);

    BaseResponse getListSurvey(SurveyRequest request);
    BaseResponse updateSurvey(SurveyRequest request);

    BaseResponse deleteSurvey(SurveyRequest request);
}
