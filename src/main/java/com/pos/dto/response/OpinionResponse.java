package com.pos.dto.response;

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
    private String content;
}
