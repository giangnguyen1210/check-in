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
    private Integer departmentId;
    private String department;
    private Integer unitId;
    private String unit;
    private Integer jobTitleId;
    private String jobTitle;
    private Integer positionId;
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
