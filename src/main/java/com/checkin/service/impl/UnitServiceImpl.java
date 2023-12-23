package com.checkin.service.impl;

import com.checkin.common.SupportService;
import com.checkin.dto.request.UnitRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.UnitResponse;
import com.checkin.mapper.UnitMapper;
import com.checkin.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private SupportService service;

    @Override
    public BaseResponse listUnit(UnitRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<UnitResponse> unit = unitMapper.listUnit(request);
        baseResponse.setData(unit);
        baseResponse.setTotalRecords(unit.size());
        return baseResponse;
    }
    @Override
    public BaseResponse createUnit(UnitRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer unitCheckExist = unitMapper.checkUnitExist(request);
        System.out.println(unitCheckExist);
        if(unitCheckExist==0){
            String id = "UNIT-";
            int getNextId = 0;
            if(unitMapper.totalUnit()>0){
                UnitResponse Unit = unitMapper.getNextCode();
                getNextId = Integer.parseInt(Unit.getCode().substring(Unit.getCode().length() - 4))+1;
            }
            String pad = service.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());

            if(request!=null && !request.getName().equals("") && request.getBranch()!=null){
                unitMapper.createUnit(request);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setErrorDesc("Create success");
            }else{
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Unit Name or Branch is null");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Create fail, Unit existed!");
        }
        return baseResponse;
    }
    @Override
    public BaseResponse updateUnit(UnitRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if(request.getName()==null){
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(), "Tên phòng ban không được trống");
        }
        if(request.getBranch()==null){
            return new BaseResponse(HttpStatus.BAD_REQUEST.name(), "Tên chi nhánh không được trống");

        }

        Integer b = unitMapper.updateUnit(request);
        if (b !=0) {
            baseResponse.setData(request);
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setTotalRecords(unitMapper.totalUnit());
            baseResponse.setErrorDesc("chỉnh sửa thành công");
        } else {
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("chỉnh sửa thất bại");
            return baseResponse;
        }
        return baseResponse;
    }
}
