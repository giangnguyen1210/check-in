package com.checkin.mapper;

import com.checkin.dto.request.StatusRequest;
import com.checkin.dto.response.StatusResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatusMapper {
    Integer createStatus(StatusRequest request);

    List<StatusResponse> listStatus();
    Integer checkStatusExist(StatusRequest request);
}
