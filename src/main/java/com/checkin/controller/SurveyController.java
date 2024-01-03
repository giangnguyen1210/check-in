package com.checkin.controller;

import com.checkin.dto.request.SurveyRequest;
import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createSurvey(@RequestBody SurveyRequest request){
        return new ResponseEntity<>(surveyService.createSurvey(request), HttpStatus.OK);
    }
    @PostMapping("/list")
    public ResponseEntity<BaseResponse> listSurvey(@RequestBody SurveyRequest request){
        return new ResponseEntity<>(surveyService.getListSurvey(request), HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateSurvey(@RequestBody SurveyRequest request){
        return new ResponseEntity<>(surveyService.updateSurvey(request), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<BaseResponse> deleteSurvey(@RequestBody SurveyRequest request){
        return new ResponseEntity<>(surveyService.deleteSurvey(request), HttpStatus.OK);
    }
}
