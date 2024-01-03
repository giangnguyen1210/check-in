package com.checkin.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class SettingPasswordResponse {
    private Integer id;
    private Integer minLength;
    private Integer maxLength;
    private Integer minNumber;
    private Integer minChar;
    private Integer minSpecialChar;
    private Integer timeExpire;
    private Date createdAt;
    private Date updatedAt;
}
