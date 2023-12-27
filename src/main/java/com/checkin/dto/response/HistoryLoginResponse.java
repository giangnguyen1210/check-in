package com.checkin.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
@Data
public class HistoryLoginResponse {
    private Integer id;
    private String employeeCode;
    private Date date;
    private LocalTime loginTime;
    private String departmentName;
    private String unitName;
    private String fullname;
    private String keyword;
    private String departmentCode;
    private String jobTitleName;
    private Date dob;
    private String gender;
    private String unitCode;
    private Integer genderId;
    private Integer statusId;

}
