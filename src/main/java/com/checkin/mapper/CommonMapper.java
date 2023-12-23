package com.checkin.mapper;

import com.checkin.dto.response.ObjectResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {
    List<ObjectResponse> getListObject();
}
