package com.checkin.mapper;

import com.checkin.dto.request.SettingPasswordRequest;
import com.checkin.dto.response.SettingPasswordResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettingPasswordMapper {
    Integer insertSetting(SettingPasswordRequest request);
    Integer updateSetting(SettingPasswordRequest request);
    SettingPasswordResponse getSetting();
}
