package com.checkin.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class UserLoginResponse {
    private Integer id;
    private Integer numberWrongPassword;
    private String employeeCode;
    private Date date;
    private LocalTime time;
    private Integer statusId;
}
