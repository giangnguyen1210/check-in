package com.pos.controller;

import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.OpinionRequest;
import com.pos.dto.response.BaseResponse;
import com.pos.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("opinion")
public class OpinionController {
    @Autowired
    private OpinionService opinionService;
    @PostMapping("/create-opinion")
    public ResponseEntity<BaseResponse> createOpinion(@RequestBody OpinionRequest request){
        return new ResponseEntity<>(opinionService.createOpinion(request), HttpStatus.OK);
    }
}
