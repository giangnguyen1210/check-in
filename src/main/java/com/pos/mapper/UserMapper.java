package com.pos.mapper;

import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.UserRequest;
import com.pos.dto.response.UserResponse;
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

    Integer deActivateUser(UserRequest request);

    Integer checkUserExist(CheckInCheckOutRequest request);

}
