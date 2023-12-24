package com.checkin.service;

import com.checkin.dto.request.HolidayRequest;
import com.checkin.dto.response.BaseResponse;

public interface HolidayService {
    BaseResponse createHoliday(HolidayRequest request);

    BaseResponse getListHoliday(HolidayRequest request);
    BaseResponse updateHoliday(HolidayRequest request);
}
