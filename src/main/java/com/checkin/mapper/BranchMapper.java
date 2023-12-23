package com.checkin.mapper;

import com.checkin.dto.request.BranchRequest;
import com.checkin.dto.response.BranchResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BranchMapper {
    Integer createBranch(BranchRequest request);

    List<BranchResponse> listBranch();
}
