package com.pos.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class OpinionRequest {
    private Integer id;
    private String code;
    private String name;
    private String response;
    private Date createdDate;
    private String employeeCode;
    private String content;
    private String departmentName;
    private Integer page;
    private Integer limit;

}
