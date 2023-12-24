package com.checkin.service;

import com.checkin.dto.request.EventRequest;
import com.checkin.dto.response.BaseResponse;

public interface EventService {
    BaseResponse createEvent(EventRequest request);

    BaseResponse getListEvent(EventRequest request);
    BaseResponse updateEvent(EventRequest request);
}
