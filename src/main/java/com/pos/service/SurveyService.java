package com.pos.service;


import com.pos.dto.request.OpinionRequest;
import com.pos.dto.response.BaseResponse;

public interface SurveyService {

    BaseResponse createOpinion(OpinionRequest request);


}
