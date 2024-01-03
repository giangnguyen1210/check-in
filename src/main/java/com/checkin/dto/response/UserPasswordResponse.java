package com.checkin.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserPasswordResponse {
    private Integer id;
    private Date updateTime;
    private String employeeCode;
}
