package com.checkin.mapper;

import com.checkin.dto.request.UserLoginRequest;
import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.UserLoginResponse;
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

    Integer deActivateUser(UserRequest request);
    Integer activateUser(UserRequest request);
    Integer countUser(UserRequest request);

    Integer insertTimeResetPassword(String employeeCode);

    Integer insertLoginHistory(String employeeCode, String email);

    Integer updateLoginHistory(UserRequest request);

    Integer updateLoginIfRight(UserRequest request);

    UserLoginResponse getUserLoginHistory(String employeeCode, String email);

}
