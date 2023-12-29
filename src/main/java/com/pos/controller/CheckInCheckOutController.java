package com.pos.controller;

import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.response.BaseResponse;
import com.pos.service.AuthService;
import com.pos.service.CheckinCheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkin-checkout")
public class CheckInCheckOutController {
    @Autowired
    private CheckinCheckoutService checkinCheckoutService;
    @PostMapping("/checkout")
    public ResponseEntity<BaseResponse> checkOut(@RequestBody CheckInCheckOutRequest request){
        return new ResponseEntity<>(checkinCheckoutService.checkOut(request), HttpStatus.OK);
    }

    @PostMapping("/checkin")
    public ResponseEntity<BaseResponse> checkIn(@RequestBody CheckInCheckOutRequest request){
        return new ResponseEntity<>(checkinCheckoutService.checkIn(request), HttpStatus.OK);
    }

    @PostMapping("/list-checkin")
    public ResponseEntity<BaseResponse> listCheckin(@RequestBody CheckInCheckOutRequest request){
        return new ResponseEntity<>(checkinCheckoutService.getListCheckin(request), HttpStatus.OK);
    }
}
