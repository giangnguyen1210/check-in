package com.checkin.mapper;

import com.checkin.dto.request.UnitRequest;
import com.checkin.dto.response.UnitResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnitMapper {
    Integer createUnit(UnitRequest request);

    List<UnitResponse> listUnit();

    Integer checkUnitExist(UnitRequest unitRequest);
}
