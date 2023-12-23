package com.checkin.controller;

import com.checkin.dto.request.UnitRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/unit")
public class UnitController {
    @Autowired
    private UnitService unitService;
    @PostMapping(value = "/list-unit")
    public ResponseEntity<BaseResponse> getListUnit(@RequestBody UnitRequest request){
        return new ResponseEntity<>(unitService.listUnit(request), HttpStatus.OK);
    }
    @PostMapping(value = "/create-unit")
    public ResponseEntity<BaseResponse> createUnit(@RequestBody UnitRequest request){
        return new ResponseEntity<>(unitService.createUnit(request), HttpStatus.OK);
    }
    @PostMapping(value = "/update-unit")
    public ResponseEntity<BaseResponse> updateUnit(@RequestBody UnitRequest request){
        return new ResponseEntity<>(unitService.updateUnit(request), HttpStatus.OK);
    }
}
