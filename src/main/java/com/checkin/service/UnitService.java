package com.checkin.service;

import com.checkin.dto.request.UnitRequest;
import com.checkin.dto.response.BaseResponse;

public interface UnitService {

    BaseResponse listUnit(UnitRequest request);


    BaseResponse createUnit(UnitRequest request);

    BaseResponse updateUnit(UnitRequest request);

//    BaseResponse getListObject();
}
