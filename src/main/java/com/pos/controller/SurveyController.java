package com.pos.controller;

import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.SurveyRequest;
import com.pos.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey")
public class SurveyController {
//    @PostMapping("/get-survey")
//    public ResponseEntity<BaseResponse> getSurvey(@RequestBody SurveyRequest request){
//        return new ResponseEntity<>("ABFC", HttpStatus.OK);
//    }


}
