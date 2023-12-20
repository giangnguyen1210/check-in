package com.checkin.mapper;

import com.checkin.dto.request.GenderRequest;
import com.checkin.dto.request.JobTitleRequest;
import com.checkin.dto.response.JobTitleResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobTitleMapper {
    Integer createJobTitle(JobTitleRequest request);

    List<JobTitleResponse> listJobTitle();
    Integer checkJobTitleExist(JobTitleRequest request);

    JobTitleResponse getNextCode();

}
