package com.checkin.dto.request;

import com.checkin.dto.response.HolidayResponse;
import com.checkin.dto.response.UserResponse;
import lombok.Data;

@Data
public class HolidayRequest extends HolidayResponse {
    private Integer page;
    private Integer limit;
}
