package com.checkin.service;

import com.checkin.dto.request.SettingPasswordRequest;
import com.checkin.dto.request.SettingSessionRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.SettingPasswordResponse;

import java.util.Set;

public interface SettingService {
    BaseResponse insertSettingPassword(SettingPasswordRequest request);
    BaseResponse updateSettingPassword(SettingPasswordRequest request);

    BaseResponse getSettingPassword(SettingPasswordRequest request);

    BaseResponse insertSettingSession(SettingSessionRequest request);

    BaseResponse updateSettingSession(SettingSessionRequest request);

    BaseResponse getSettingSession(SettingSessionRequest request);
}
