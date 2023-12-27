package com.checkin.controller;

import com.checkin.dto.request.EventRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/event")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping("/create-event")
    public ResponseEntity<BaseResponse> createEvent(@RequestBody EventRequest request){
        return new ResponseEntity<>(eventService.createEvent(request), HttpStatus.OK);
    }
    @PostMapping("/list-event")
    public ResponseEntity<BaseResponse> listEvent(@RequestBody EventRequest request){
        return new ResponseEntity<>(eventService.getListEvent(request), HttpStatus.OK);
    }
    @PostMapping("/update-event")
    public ResponseEntity<BaseResponse> updateEvent(@RequestBody EventRequest request){
        return new ResponseEntity<>(eventService.updateEvent(request), HttpStatus.OK);
    }
}
