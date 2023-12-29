package com.pos.service.impl;

import com.pos.common.CommonService;
import com.pos.dto.request.CheckInCheckOutRequest;
import com.pos.dto.request.OpinionRequest;
import com.pos.dto.response.BaseResponse;
import com.pos.dto.response.CheckInCheckOutResponse;
import com.pos.dto.response.OpinionResponse;
import com.pos.mapper.CheckInCheckOutMapper;
import com.pos.mapper.OpinionMapper;
import com.pos.mapper.UserMapper;
import com.pos.service.CheckinCheckoutService;
import com.pos.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {
    @Autowired
    private OpinionMapper opinionMapper;
    @Autowired
    private CommonService commonService;
    @Override
    public BaseResponse createOpinion(OpinionRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        Integer departmentCheckExist = opinionMapper.checkOpinionExist(request);
        System.out.println(departmentCheckExist);
        if(departmentCheckExist==0){
            String id = "OPINION-";
            int getNextId = 0;
            if(opinionMapper.totalOpinion()>0){
                OpinionResponse opinionResponse = opinionMapper.getNextCode();
                getNextId = Integer.parseInt(opinionResponse.getCode().substring(opinionResponse.getCode().length() - 4))+1;
            }
            String pad = commonService.padLeft(String.valueOf(getNextId), 4, "0");
            request.setCode((id+pad).trim());

            if(request.getEmployeeCode()==null){
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Người dùng không được để trống");
                return baseResponse;
            }
            if(request!=null && request.getName()!=null && request.getContent()!=null){
                opinionMapper.createOpinion(request);
                baseResponse.setErrorCode(HttpStatus.OK.name());
                baseResponse.setErrorDesc("Tạo mới góp ý thành công");
            }else{
                baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
                baseResponse.setErrorDesc("Tạo mới thất bại, Tên góp ý hoặc nội dung không được để trống");
            }
        }else{
            baseResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
            baseResponse.setErrorDesc("Tạo mới thất bại, Tên góp ý đã tồn tại");
        }
        return baseResponse;
    }

}
