package com.checkin.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class EventResponse {
    private Integer id;
    private String name;
    private String code;
    private String note;
    private Date startDate;
    private LocalTime startTime;
    private Date endDate;
    private LocalTime endTime;
}
