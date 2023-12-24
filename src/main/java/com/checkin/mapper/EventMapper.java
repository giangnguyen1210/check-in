package com.checkin.mapper;

import com.checkin.dto.request.EventRequest;
import com.checkin.dto.response.EventResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {
    Integer createEvent(EventRequest request);
    EventResponse getNextCode();
    Integer totalEvent();

    List<EventResponse> getListEvent(EventRequest request);

    Integer updateEvent(EventRequest request);

    Integer checkEventExist(EventRequest request);




}
