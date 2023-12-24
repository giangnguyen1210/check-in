package com.checkin.controller;

import com.checkin.dto.request.HolidayRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.HolidayService;
import com.checkin.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/holiday")
public class HolidayController {
    @Autowired
    private HolidayService holidayService;
    @PostMapping("/create-holiday")
    public ResponseEntity<BaseResponse> createHoliday(@RequestBody HolidayRequest request){
        return new ResponseEntity<>(holidayService.createHoliday(request), HttpStatus.OK);
    }
    @PostMapping("/list-holiday")
    public ResponseEntity<BaseResponse> listHoliday(@RequestBody HolidayRequest request){
        return new ResponseEntity<>(holidayService.getListHoliday(request), HttpStatus.OK);
    }
    @PostMapping("/update-holiday")
    public ResponseEntity<BaseResponse> updateHoliday(@RequestBody HolidayRequest request){
        return new ResponseEntity<>(holidayService.updateHoliday(request), HttpStatus.OK);
    }
}
