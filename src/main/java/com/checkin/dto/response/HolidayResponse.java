package com.checkin.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class HolidayResponse {
    private Integer id;
    private String name;
    private String code;
    private String note;
    private Date date;
}
