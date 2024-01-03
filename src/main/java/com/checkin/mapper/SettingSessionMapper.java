package com.checkin.mapper;

import com.checkin.dto.request.SettingPasswordRequest;
import com.checkin.dto.request.SettingSessionRequest;
import com.checkin.dto.response.SettingPasswordResponse;
import com.checkin.dto.response.SettingSessionResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingSessionMapper {
    Integer insertSetting(SettingSessionRequest request);
    Integer updateSetting(SettingSessionRequest request);
    SettingSessionResponse getSetting();
}
