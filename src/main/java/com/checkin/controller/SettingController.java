package com.checkin.controller;

import com.checkin.dto.request.SettingPasswordRequest;
import com.checkin.dto.request.SettingSessionRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/setting")
public class SettingController {
    @Autowired
    private SettingService settingService;
    @PostMapping("/update-password")
    public ResponseEntity<BaseResponse> updateSettingPassword(@RequestBody SettingPasswordRequest request){
        return new ResponseEntity<>(settingService.updateSettingPassword(request), HttpStatus.OK);
    }

    @PostMapping("/create-password")
    public ResponseEntity<BaseResponse> createSettingPassword(@RequestBody SettingPasswordRequest request){
        return new ResponseEntity<>(settingService.insertSettingPassword(request), HttpStatus.OK);
    }
    @PostMapping("/get-password")
    public ResponseEntity<BaseResponse> getSettingPassword(@RequestBody SettingPasswordRequest request){
        return new ResponseEntity<>(settingService.getSettingPassword(request), HttpStatus.OK);
    }

    @PostMapping("/update-session")
    public ResponseEntity<BaseResponse> updateSettingSession(@RequestBody SettingSessionRequest request){
        return new ResponseEntity<>(settingService.updateSettingSession(request), HttpStatus.OK);
    }

    @PostMapping("/create-session")
    public ResponseEntity<BaseResponse> createSettingSession(@RequestBody SettingSessionRequest request){
        return new ResponseEntity<>(settingService.insertSettingSession(request), HttpStatus.OK);
    }
    @PostMapping("/get-session")
    public ResponseEntity<BaseResponse> getSettingSession(@RequestBody SettingSessionRequest request){
        return new ResponseEntity<>(settingService.getSettingSession(request), HttpStatus.OK);
    }
}
