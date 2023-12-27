package com.checkin.dto.request;

import com.checkin.dto.response.HistoryLoginResponse;
import lombok.Data;

@Data
public class HistoryLoginRequest extends HistoryLoginResponse {
    private Integer page;
    private Integer limit;
}
