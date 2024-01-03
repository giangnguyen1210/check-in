package com.checkin.dto.request;

import com.checkin.dto.response.UnitResponse;
import lombok.Data;

@Data
public class UnitRequest extends UnitResponse {
    private Integer page;
    private Integer limit;
}