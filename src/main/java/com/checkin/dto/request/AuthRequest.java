package com.checkin.dto.request;

import com.checkin.dto.response.BaseResponse;
import lombok.Data;

@Data
public class AuthRequest extends BaseResponse {
    private String accessToken;
    private String tokenType = "Bearer ";
}
