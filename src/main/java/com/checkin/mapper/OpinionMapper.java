package com.checkin.mapper;

import com.checkin.dto.request.OpinionRequest;
import com.checkin.dto.response.OpinionResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpinionMapper {
    Integer responseOpinion(OpinionRequest request);


    List<OpinionResponse> listOpinion(OpinionRequest request);
}
