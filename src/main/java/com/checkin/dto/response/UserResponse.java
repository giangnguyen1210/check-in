package com.checkin.dto.response;

import lombok.Data;

import java.util.Date;
@Data
public class UserResponse {
    private Integer id;
    private String employeeCode;
    private String fullname;
    private Date dob;
    private String phone;
    private Integer genderId;
    private String gender;
    private String departmentCode;
    private String department;
    private String unitCode;
    private String unit;
    private String jobTitleCode;
    private String jobTitle;
    private String positionCode;
    private String position;
    private String password;
    private String rePassword;
    private String email;
    private String avatar;
    private Integer roleId;
    private String role;
    private Integer statusId;
    private String status;
    private Integer cccd;
    private String cccdImage;
    private String faceImage;
    private String otp;
    private String keyword;


}
