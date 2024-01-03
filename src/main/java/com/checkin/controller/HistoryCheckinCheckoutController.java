package com.checkin.controller;

import com.checkin.dto.request.CheckinCheckoutRequest;
import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.HistoryCheckinCheckoutService;
import com.checkin.service.HistoryLoginService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    @PostMapping(value ="/export-detail", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> exportHistoryCheckinDetail(@RequestBody CheckinCheckoutRequest request) {
        try {
            File file = checkinCheckoutService.exportHistoryCheckinCheckoutDetail(request);
            if (file == null) {
                throw new ServiceException("Nothing to export");
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return new ResponseEntity<>(resource, HttpStatus.OK);

        } catch (FileNotFoundException e) {
            throw new ServiceException("Failed");
        }
    }
    @PostMapping(value ="/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> exportHistoryCheckin(@RequestBody CheckinCheckoutRequest request) {
        try {
            File file = checkinCheckoutService.exportHistoryCheckinCheckout(request);
            if (file == null) {
                throw new ServiceException("Nothing to export");
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return new ResponseEntity<>(resource, HttpStatus.OK);

        } catch (FileNotFoundException e) {
            throw new ServiceException("Failed");
        }
    }
}
