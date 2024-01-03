package com.checkin.service.impl;

import com.checkin.dto.request.CheckinCheckoutRequest;
import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.CheckinCheckoutResponse;
import com.checkin.dto.response.HistoryLoginResponse;
import com.checkin.mapper.CheckinCheckoutMapper;
import com.checkin.service.HistoryCheckinCheckoutService;
import com.checkin.utils.ExportUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistoryCheckinCheckoutServiceImpl implements HistoryCheckinCheckoutService {
    @Autowired
    private CheckinCheckoutMapper checkinCheckoutMapper;
    @Override
    public BaseResponse getListCheckinCheckout(CheckinCheckoutRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<CheckinCheckoutResponse> list = checkinCheckoutMapper.listCheckinCheckout(request);
        baseResponse.setTotalRecords(checkinCheckoutMapper.totalCheckin(request).size());
        baseResponse.setErrorCode(HttpStatus.OK.name());
        baseResponse.setData(list);
        return baseResponse;
    }
    @Override
    public BaseResponse getListCheckinCheckoutDetail(CheckinCheckoutRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<CheckinCheckoutResponse> list = checkinCheckoutMapper.listCheckinCheckoutDetail(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setErrorCode(HttpStatus.OK.name());
        baseResponse.setData(list);
        return baseResponse;
    }



    @Override
    public File exportHistoryCheckinCheckoutDetail(CheckinCheckoutRequest request) {
        File file = null;
        try {
            file = File.createTempFile("out", ".tmp");
            file.deleteOnExit();
            Resource resource = new ClassPathResource("templates/history_checkin_detail.jasper");
            FileOutputStream fos = new FileOutputStream(file);
            InputStream inputStream = resource.getInputStream();
            System.out.println("inputStream"+inputStream);
            List<CheckinCheckoutResponse> list = checkinCheckoutMapper.listCheckinCheckoutDetail(request);
            Map<String, Object> parameters = new HashMap<>();
            ExportUtils.exportReport(inputStream, fos, parameters, list, "pdf");


        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return file;
    }

    @Override
    public File exportHistoryCheckinCheckout(CheckinCheckoutRequest request) {
        File file = null;
        try {
            file = File.createTempFile("out", ".tmp");
            file.deleteOnExit();
            Resource resource = new ClassPathResource("templates/history_checkin.jasper");
            FileOutputStream fos = new FileOutputStream(file);
            InputStream inputStream = resource.getInputStream();
            System.out.println("inputStream"+inputStream);
            List<CheckinCheckoutResponse> list = checkinCheckoutMapper.listCheckinCheckout(request);
            Map<String, Object> parameters = new HashMap<>();
            ExportUtils.exportReport(inputStream, fos, parameters, list, "pdf");


        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return file;
    }

}
