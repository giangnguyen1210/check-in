package com.pos.service;


import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.OpinionRequest;
import com.pos.dto.response.BaseResponse;

public interface OpinionService {

    BaseResponse createOpinion(OpinionRequest request);


}
