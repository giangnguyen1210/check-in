package com.checkin.mapper;

import com.checkin.dto.request.SurveyRequest;
import com.checkin.dto.request.UnitRequest;
import com.checkin.dto.response.SurveyResponse;
import com.checkin.dto.response.UnitResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SurveyMapper {
    Integer createSurvey(SurveyRequest request);
    SurveyResponse getNextCode();
    Integer totalSurvey();

    List<SurveyResponse> getListSurvey(SurveyRequest request);

    Integer updateSurvey(SurveyRequest request);



}
