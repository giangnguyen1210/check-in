package com.checkin.service.impl;

import com.checkin.dto.request.CheckinCheckoutRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.CheckinCheckoutResponse;
import com.checkin.mapper.CheckinCheckoutMapper;
import com.checkin.service.HistoryCheckinCheckoutService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class HistoryCheckinCheckoutServiceImpl implements HistoryCheckinCheckoutService {
    @Autowired
    private CheckinCheckoutMapper checkinCheckoutMapper;
    @Override
    public BaseResponse getListCheckinCheckout(CheckinCheckoutRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<CheckinCheckoutResponse> list = checkinCheckoutMapper.listCheckinCheckout(request);
        baseResponse.setTotalRecords(list.size());
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
    public BaseResponse exportHistoryCheckinCheckout(CheckinCheckoutRequest request) {
        try {
            BaseResponse baseResponse = new BaseResponse();
            String path = "/Users/hongnguyen/Desktop/downloads/checkin-checkout/";
            List<CheckinCheckoutResponse> responses = checkinCheckoutMapper.listCheckinCheckout(request);
            File file = ResourceUtils.getFile("classpath:templates/history_checkin.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(responses);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"history_checkin.pdf");
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setErrorDesc("Export successful. File saved as: " + "history_checkin.pdf");
            return baseResponse;
        } catch (JRException | FileNotFoundException e) {
            // Handle exceptions gracefully
            e.printStackTrace(); // Log the exception or use a logging framework
            return new BaseResponse("Error during export: " + e.getMessage());
        }
    }

    @Override
    public BaseResponse exportHistoryCheckinCheckoutDetail(CheckinCheckoutRequest request) {
        try {
            BaseResponse baseResponse = new BaseResponse();
            String path = "/Users/hongnguyen/Desktop/downloads/checkin-checkout/";
            List<CheckinCheckoutResponse> responses = checkinCheckoutMapper.listCheckinCheckoutDetail(request);
            File file = ResourceUtils.getFile("classpath:templates/history_checkin_detail.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(responses);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"history_checkin_detail.pdf");
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setErrorDesc("Export successful. File saved as: " + "history_checkin_detail.pdf");
            return baseResponse;
        } catch (JRException | FileNotFoundException e) {
            // Handle exceptions gracefully
            e.printStackTrace(); // Log the exception or use a logging framework
            return new BaseResponse("Error during export: " + e.getMessage());
        }
    }



}
