package com.checkin.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class CheckinCheckoutResponse {
    private Integer id;
    private String employeeCode;
    private Date date;
    private LocalTime checkinTime;
    private LocalTime checkoutTime;
    private LocalTime workingTime;
    private LocalTime lateTime;
    private LocalTime earlyOutTime;
    private String status;
    private String departmentName;
    private String unitName;
    private String fullname;
    private String keyword;
    private String departmentCode;
    private String unitCode;
    private Integer genderId;
    private Integer statusId;
}
