package com.pos.mapper;

import com.pos.dto.response.SurveyResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SurveyMapper {
    List<SurveyResponse> listSurvey();
}
