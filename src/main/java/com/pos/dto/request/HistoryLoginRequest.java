package com.pos.dto.request;

import com.pos.dto.response.HistoryLoginResponse;
import lombok.Data;

@Data
public class HistoryLoginRequest extends HistoryLoginResponse {
    private Integer page;
    private Integer limit;
}
