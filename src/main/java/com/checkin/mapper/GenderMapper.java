package com.checkin.mapper;

import com.checkin.dto.request.GenderRequest;
import com.checkin.dto.response.GenderResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenderMapper {
    Integer createGender(GenderRequest request);

    List<GenderResponse> listGender();

    Integer checkGenderExist(GenderRequest request);
}
