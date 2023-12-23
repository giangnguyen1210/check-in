package com.checkin.dto.request;

import com.checkin.dto.response.DepartmentResponse;
import lombok.Data;

@Data
public class DepartmentRequest extends DepartmentResponse {
    private Integer page;
    private Integer limit;

}
