package com.checkin.dto.request;

import com.checkin.dto.response.UserResponse;
import lombok.Data;

import java.util.Date;
@Data
public class UserRequest extends UserResponse {
    private Integer page;
    private Integer limit;
}
