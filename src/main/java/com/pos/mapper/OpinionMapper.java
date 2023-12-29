package com.pos.mapper;

import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.OpinionRequest;
import com.pos.dto.response.CheckInCheckOutResponse;
import com.pos.dto.response.OpinionResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpinionMapper {
    Integer createOpinion(OpinionRequest request);

    Integer checkOpinionExist(OpinionRequest request);

    Integer totalOpinion();

    OpinionResponse getNextCode();
}
