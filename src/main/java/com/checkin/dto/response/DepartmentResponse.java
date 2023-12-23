package com.checkin.dto.response;

import lombok.Data;

@Data
public class DepartmentResponse {
    private Integer id;
    private String name;
    private String code;
    private String branch;
    private String branchName;
    private String note;
}
