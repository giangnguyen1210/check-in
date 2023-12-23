package com.checkin.controller;

import com.checkin.dto.request.RegisterRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/register")
public class RegisterController {
    @Autowired
    private RegisterService RegisterService;
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createRegister(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(RegisterService.createRegister(request), HttpStatus.OK);
    }
    @PostMapping("/list")
    public ResponseEntity<BaseResponse> listRegister(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(RegisterService.getListRegister(request), HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateRegister(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(RegisterService.updateRegister(request), HttpStatus.OK);
    }
}
