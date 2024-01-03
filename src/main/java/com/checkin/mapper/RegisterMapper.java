package com.checkin.mapper;

import com.checkin.dto.request.RegisterRequest;
import com.checkin.dto.response.RegisterResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterMapper {
    Integer createRegister(RegisterRequest request);
    RegisterResponse getNextCode();
    Integer totalRegister();

    List<RegisterResponse> getListRegister(RegisterRequest request);

    Integer updateRegister(RegisterRequest request);


    Integer deleteRegister(RegisterRequest request);
}
