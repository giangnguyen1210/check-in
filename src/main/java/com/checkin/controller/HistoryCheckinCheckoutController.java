package com.checkin.controller;

import com.checkin.dto.request.CheckinCheckoutRequest;
import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.HistoryCheckinCheckoutService;
import com.checkin.service.HistoryLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/history-checkin-checkout")
public class HistoryCheckinCheckoutController {
    @Autowired
    private HistoryCheckinCheckoutService checkinCheckoutService;
    @PostMapping("/list")
    public ResponseEntity<BaseResponse> listHistory(@RequestBody CheckinCheckoutRequest request){
        return new ResponseEntity<>(checkinCheckoutService.getListCheckinCheckout(request), HttpStatus.OK);
    }

    @PostMapping("/detail")
    public ResponseEntity<BaseResponse> listCheckinCheckoutDetail(@RequestBody CheckinCheckoutRequest request){
        return new ResponseEntity<>(checkinCheckoutService.getListCheckinCheckoutDetail(request), HttpStatus.OK);
    }
    @PostMapping("/export")
    public ResponseEntity<BaseResponse> exportHistoryCheckin(@RequestBody CheckinCheckoutRequest request) {
        return new ResponseEntity<>(checkinCheckoutService.exportHistoryCheckinCheckout(request), HttpStatus.OK);
    }

    @PostMapping("/export-detail")
    public ResponseEntity<BaseResponse> exportHistoryCheckinDetail(@RequestBody CheckinCheckoutRequest request) {
        return new ResponseEntity<>(checkinCheckoutService.exportHistoryCheckinCheckoutDetail(request), HttpStatus.OK);
    }
}
