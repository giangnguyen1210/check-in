package com.checkin.service.impl;

import com.checkin.common.SupportService;
import com.checkin.dto.request.EventRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.EventResponse;
import com.checkin.mapper.EventMapper;
import com.checkin.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private SupportService service;

    @Autowired
    private EventMapper eventMapper;
    @Override
    public BaseResponse createEvent(EventRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer eventCheckExist = eventMapper.checkEventExist(request);
        if(eventCheckExist==0){
            String id = "EVENT-";
            int getNextId = 0;
            if(eventMapper.totalEvent()>0){
                EventResponse Event = eventMapper.getNextCode();
                getNextId = Integer.parseInt(Event.getCode().substring(Event.getCode().length() - 4))+1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());

            if(request!=null && !request.getName().equals("")){
                eventMapper.createEvent(request);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setErrorDesc("Create success");
            }else{
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Department Name or Branch is null");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail, Department existed!");
        }

        return baseResponse;
    }
    @Override
    public BaseResponse getListEvent(EventRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<EventResponse> list = eventMapper.getListEvent(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setData(list);
        return baseResponse;
    }

    @Override
    public BaseResponse updateEvent(EventRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer eventCheckExist = eventMapper.checkEventExist(request);
        if(eventCheckExist==0){
            if(request!=null && !request.getName().equals("")){
                eventMapper.updateEvent(request);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setErrorDesc("Create success");
            }else{
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Event name is null");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail, Event existed!");
        }

        return baseResponse;
    }
}
