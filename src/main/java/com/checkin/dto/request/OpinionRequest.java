package com.checkin.dto.request;

import com.checkin.dto.response.DepartmentResponse;
import com.checkin.dto.response.OpinionResponse;
import lombok.Data;

@Data
public class OpinionRequest extends OpinionResponse {

    private Integer page;
    private Integer limit;

}
