package com.pos.mapper;

import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.response.CheckInCheckOutResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckInCheckOutMapper {
    Integer checkIn(CheckInCheckOutRequest request);

    Integer checkCheckInExist(CheckInCheckOutRequest request);

    Integer checkCheckoutExist(CheckInCheckOutRequest request);

    Integer checkOut(CheckInCheckOutRequest request);

    List<CheckInCheckOutResponse> listHistoryCheckin(CheckInCheckOutRequest request);
}
