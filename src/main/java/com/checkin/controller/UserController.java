package com.checkin.controller;

import com.checkin.dto.request.UserRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.updateUser(request), HttpStatus.OK);
    }
    @PostMapping("/list")
    public ResponseEntity<BaseResponse> listUser(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.getListUser(request), HttpStatus.OK);
    }

    @PostMapping("/deactivate")
    public ResponseEntity<BaseResponse> deActivate(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.deActivateUser(request), HttpStatus.OK);
    }

    @PostMapping("/activate")
    public ResponseEntity<BaseResponse> activate(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.activateUser(request), HttpStatus.OK);
    }

}
