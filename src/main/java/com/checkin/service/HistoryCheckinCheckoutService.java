package com.checkin.service;

import com.checkin.dto.request.CheckinCheckoutRequest;
import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;

public interface HistoryCheckinCheckoutService {
    BaseResponse getListCheckinCheckout(CheckinCheckoutRequest request);

    BaseResponse getListCheckinCheckoutDetail(CheckinCheckoutRequest request);
}
