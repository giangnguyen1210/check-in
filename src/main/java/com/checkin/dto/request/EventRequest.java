package com.checkin.dto.request;

import com.checkin.dto.response.EventResponse;
import com.checkin.dto.response.HolidayResponse;
import lombok.Data;

@Data
public class EventRequest extends EventResponse {
    private Integer page;
    private Integer limit;
}
