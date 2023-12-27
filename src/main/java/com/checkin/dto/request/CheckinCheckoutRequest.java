package com.checkin.dto.request;

import com.checkin.dto.response.CheckinCheckoutResponse;
import com.checkin.dto.response.HistoryLoginResponse;
import lombok.Data;

@Data
public class CheckinCheckoutRequest extends CheckinCheckoutResponse {
    private Integer page;
    private Integer limit;
}
