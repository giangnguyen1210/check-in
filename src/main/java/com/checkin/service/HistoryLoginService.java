package com.checkin.service;

import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;

public interface HistoryLoginService {
    BaseResponse getListHistoryLogin(HistoryLoginRequest request);

    BaseResponse getHistoryDetail(HistoryLoginRequest request);
}
