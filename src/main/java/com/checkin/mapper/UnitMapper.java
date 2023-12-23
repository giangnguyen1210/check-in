package com.checkin.mapper;

import com.checkin.dto.request.UnitRequest;
import com.checkin.dto.response.UnitResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnitMapper {
    Integer createUnit(UnitRequest request);

    List<UnitResponse> listUnit(UnitRequest request);
    Integer totalUnit();

    UnitResponse getNextCode();
    Integer checkUnitExist(UnitRequest unitRequest);

    Integer updateUnit(UnitRequest request);
}
