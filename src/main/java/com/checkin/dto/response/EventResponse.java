package com.checkin.dto.response;

import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

@Data
public class EventResponse {
    private Integer id;
    private String name;
    private String code;
    private String note;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
}
