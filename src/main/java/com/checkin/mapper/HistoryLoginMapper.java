package com.checkin.mapper;

import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.HistoryLoginResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HistoryLoginMapper {
    List<HistoryLoginResponse> listHistoryLogin(HistoryLoginRequest request);

    List<HistoryLoginResponse> listHistoryLoginDetail(HistoryLoginRequest request);
    List<HistoryLoginResponse> countHistoryLogin(HistoryLoginRequest request);
}
