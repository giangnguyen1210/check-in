package com.pos.mapper;

import com.pos.dto.request.HistoryLoginRequest;
import com.pos.dto.request.UserRequest;
import com.pos.dto.response.HistoryLoginResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryLoginMapper {
    Integer createHistoryLogin(UserRequest request);

    Integer checkHistoryLoginExist(UserRequest request);

    Integer updateHistoryLogin(UserRequest request);
}
