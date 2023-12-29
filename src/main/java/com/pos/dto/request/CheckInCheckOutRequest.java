package com.pos.dto.request;

import com.pos.dto.response.CheckInCheckOutResponse;
import lombok.Data;

import java.util.Date;

@Data
public class CheckInCheckOutRequest extends CheckInCheckOutResponse {
    private Date fromDate;
    private Date toDate;
    private Integer month;
    private Integer year;
    private Integer page;
    private Integer limit;
}
