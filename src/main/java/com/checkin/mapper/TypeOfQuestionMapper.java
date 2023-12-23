package com.checkin.mapper;

import com.checkin.dto.request.StatusRequest;
import com.checkin.dto.request.TypeOfQuestionRequest;
import com.checkin.dto.response.StatusResponse;
import com.checkin.dto.response.TypeOfQuestionResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeOfQuestionMapper {
    Integer createTypeOfQuestion(TypeOfQuestionRequest request);

    List<TypeOfQuestionResponse> listTypeOfQuestion();
}
