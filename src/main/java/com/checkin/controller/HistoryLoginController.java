package com.checkin.controller;

import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.HistoryLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/history-login")
public class HistoryLoginController {
    @Autowired
    private HistoryLoginService historyLoginService;
    @PostMapping("/list")
    public ResponseEntity<BaseResponse> listHistory(@RequestBody HistoryLoginRequest request){
        return new ResponseEntity<>(historyLoginService.getListHistoryLogin(request), HttpStatus.OK);
    }
    @PostMapping("/detail")
    public ResponseEntity<BaseResponse> listHistoryDetail(@RequestBody HistoryLoginRequest request){
        return new ResponseEntity<>(historyLoginService.getHistoryDetail(request), HttpStatus.OK);
    }
}
