package com.checkin.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class SettingSessionResponse {
    private Integer id;
    private Integer intervalTime;
    private Integer maxWrongTime;
    private Integer maxTime;
    private Integer timeLoginAgain;
    private Date createdAt;
    private Date updatedAt;
}
