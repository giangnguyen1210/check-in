package com.pos.dto.request;

import com.pos.dto.response.UserResponse;
import lombok.Data;

@Data
public class UserRequest extends UserResponse {
    private Integer page;
    private Integer limit;
}
