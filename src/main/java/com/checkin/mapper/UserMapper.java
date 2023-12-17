package com.checkin.mapper;

import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.UserResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int createUser(UserRequest userRequest);

    UserResponse findByEmail(String email);

}
