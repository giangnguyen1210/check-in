package com.checkin.mapper;

import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.UnitResponse;
import com.checkin.dto.response.UserResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer createUser(UserRequest userRequest);

    UserResponse findByEmail(String email);

    Integer checkEmailExist(UserRequest userRequest);
    Integer totalUser();

    Integer resetPassword(UserRequest request);

    List<UserResponse> getListUser(UserRequest request);

    UserResponse getNextCode();


    Integer updateUser(UserRequest request);

}
