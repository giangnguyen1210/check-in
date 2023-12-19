package com.checkin.mapper;

import com.checkin.dto.request.RoleRequest;
import com.checkin.dto.response.RoleResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    Integer createRole(RoleRequest request);

    List<RoleResponse> listRole();
    Integer checkRoleExist(RoleRequest request);
}
