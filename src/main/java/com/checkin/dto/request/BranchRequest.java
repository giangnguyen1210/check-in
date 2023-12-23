package com.checkin.dto.request;

import com.checkin.dto.response.BranchResponse;
import lombok.Data;

@Data
public class BranchRequest extends BranchResponse {
    private Integer page;
    private Integer limit;
}
