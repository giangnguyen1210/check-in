package com.checkin.dto.response;

import lombok.Data;

import java.util.Date;
@Data
public class UserResponse {
    private int id;
    private String employeeCode;
    private String fullname;
    private Date dob;
    private String phone;
    private int genderId;
    private String gender;
    private int departmentId;
    private String department;
    private int unitId;
    private String unit;
    private int jobTitleId;
    private String jobTitle;
    private int positionId;
    private String position;
    private String password;
    private String email;
    private String avatar;
    private int roleId;
    private String role;
    private int statusId;
    private String status;
    private int cccd;
    private String cccdImage;
    private String faceImage;


}
