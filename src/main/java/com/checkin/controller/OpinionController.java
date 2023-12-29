package com.checkin.controller;

import com.checkin.dto.request.HolidayRequest;
import com.checkin.dto.request.OpinionRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.HolidayService;
import com.checkin.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/opinion")
public class OpinionController {
    @Autowired
    private OpinionService opinionService;
    @PostMapping("/response-opinion")
    public ResponseEntity<BaseResponse> responseOpinion(@RequestBody OpinionRequest request){
        return new ResponseEntity<>(opinionService.responseOpinion(request), HttpStatus.OK);
    }
    @PostMapping("/list-opinion")
    public ResponseEntity<BaseResponse> listOpinion(@RequestBody OpinionRequest request){
        return new ResponseEntity<>(opinionService.listOpinion(request), HttpStatus.OK);
    }
}
