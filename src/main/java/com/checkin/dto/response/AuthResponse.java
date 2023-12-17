package com.checkin.dto.response;

import lombok.Data;

@Data
public class AuthResponse extends BaseResponse{
    private String accessToken;
    private String tokenType = "Bearer ";
}
