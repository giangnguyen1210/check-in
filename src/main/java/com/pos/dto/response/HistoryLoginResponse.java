package com.pos.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
@Data
public class HistoryLoginResponse {
    private Integer id;
    private String employeeCode;
    private Date date;
    private LocalTime loginTime;
}
