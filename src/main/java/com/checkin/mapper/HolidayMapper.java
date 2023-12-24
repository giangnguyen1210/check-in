package com.checkin.mapper;

import com.checkin.dto.request.HolidayRequest;
import com.checkin.dto.response.HolidayResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HolidayMapper {
    Integer createHoliday(HolidayRequest request);
    HolidayResponse getNextCode();
    Integer totalHoliday();

    List<HolidayResponse> getListHoliday(HolidayRequest request);

    Integer updateHoliday(HolidayRequest request);

    Integer checkHolidayExist(HolidayRequest request);



}
