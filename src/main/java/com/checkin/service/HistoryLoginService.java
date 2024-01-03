package com.checkin.service;

import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.FileNotFoundException;

public interface HistoryLoginService {
    BaseResponse getListHistoryLogin(HistoryLoginRequest request);

    BaseResponse getHistoryDetail(HistoryLoginRequest request);


    File exportHistoryLoginDetail(HistoryLoginRequest request);

    File exportHistoryLogin(HistoryLoginRequest request);
}
