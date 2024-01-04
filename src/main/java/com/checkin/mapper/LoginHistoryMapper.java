package com.checkin.mapper;

import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.HistoryLoginResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginHistoryMapper {
    Integer createHistoryLogin(UserRequest request);

    Integer countHistoryLogin(UserRequest request);

}
