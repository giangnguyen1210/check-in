package com.checkin.mapper;

import com.checkin.dto.request.DepartmentRequest;
import com.checkin.dto.request.SurveyRequest;
import com.checkin.dto.response.DepartmentResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    Integer createDepartment(DepartmentRequest request);

    List<DepartmentResponse> listDepartment(DepartmentRequest request);
    Integer totalDepartment();
    Integer checkDepartmentExist(DepartmentRequest request);
    DepartmentResponse getNextCode();
    Integer updateDepartment(DepartmentRequest request);
}
