package com.pos.dto.response;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class CheckInCheckOutResponse {
    private Integer id;
    private String employeeCode;
    private Date date;
    private LocalTime checkinTime;
    private LocalTime checkoutTime;
    private LocalTime workingTime;
    private LocalTime lateTime;
    private LocalTime earlyOutTime;
    private String status;
}
