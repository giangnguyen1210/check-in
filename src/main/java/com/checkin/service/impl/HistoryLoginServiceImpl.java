package com.checkin.service.impl;

import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.HistoryLoginResponse;
import com.checkin.mapper.HistoryLoginMapper;
import com.checkin.service.HistoryLoginService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;


@Service
public class HistoryLoginServiceImpl implements HistoryLoginService {
    @Autowired
    private HistoryLoginMapper historyLoginMapper;

    @Override
    public BaseResponse getListHistoryLogin(HistoryLoginRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<HistoryLoginResponse> list = historyLoginMapper.listHistoryLogin(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setErrorCode(HttpStatus.OK.name());
        baseResponse.setData(list);
        return baseResponse;
    }

    @Override
    public BaseResponse getHistoryDetail(HistoryLoginRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<HistoryLoginResponse> list = historyLoginMapper.listHistoryLoginDetail(request);
        baseResponse.setTotalRecords(list.size());
        baseResponse.setErrorCode(HttpStatus.OK.name());
        baseResponse.setData(list);
        return baseResponse;
    }
    @Override
    public BaseResponse exportHistoryLogin(HistoryLoginRequest request) {
        try {
            BaseResponse baseResponse = new BaseResponse();
            String path = "/Users/hongnguyen/Desktop/downloads/login/";
            List<HistoryLoginResponse> responses = historyLoginMapper.listHistoryLogin(request);
            File file = ResourceUtils.getFile("classpath:templates/history_login.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(responses);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"history_login.pdf");
            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setErrorDesc("Export successful. File saved as: " + "history_login.pdf");
            return baseResponse;
        } catch (JRException | FileNotFoundException e) {
            // Handle exceptions gracefully
            e.printStackTrace(); // Log the exception or use a logging framework
            return new BaseResponse("Error during export: " + e.getMessage());
        }
    }
    @Override
    public BaseResponse exportHistoryLoginDetail(HistoryLoginRequest request) {
        try {
            BaseResponse baseResponse = new BaseResponse();
            String path = "/Users/hongnguyen/Desktop/downloads/login/";
            List<HistoryLoginResponse> responses = historyLoginMapper.listHistoryLoginDetail(request);
            File file = ResourceUtils.getFile("classpath:templates/history_login_detail.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(responses);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"history_login_detail.pdf");

            baseResponse.setErrorCode(HttpStatus.OK.name());
            baseResponse.setErrorDesc("Export successful. File saved as: " + "history_login_detail.pdf");
            return baseResponse;
        } catch (JRException | FileNotFoundException e) {
            // Handle exceptions gracefully
            e.printStackTrace(); // Log the exception or use a logging framework
            return new BaseResponse("Error during export: " + e.getMessage());
        }
    }
}
