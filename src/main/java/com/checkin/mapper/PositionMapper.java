package com.checkin.mapper;

import com.checkin.dto.request.PositionRequest;
import com.checkin.dto.response.PositionResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionMapper {
    Integer createPosition(PositionRequest request);

    List<PositionResponse> listPosition();

    Integer checkPositionExist(PositionRequest request);

    PositionResponse getNextCode();
}
