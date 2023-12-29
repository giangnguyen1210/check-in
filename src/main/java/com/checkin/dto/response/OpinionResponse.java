package com.checkin.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class OpinionResponse {
    private Integer id;
    private String code;
    private String name;
    private String response;
    private Date createdDate;
    private String employeeCode;
    private String fullname;
    private String content;
    private String departmentName;
    private String departmentCode;
    private String adminCode;
    private Date responseDate;
    private Integer statusId;
    private String statusName;
}
