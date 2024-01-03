package com.checkin.service.impl;

import com.checkin.dto.request.HistoryLoginRequest;
import com.checkin.dto.response.BaseResponse;
import com.checkin.dto.response.HistoryLoginResponse;
import com.checkin.mapper.HistoryLoginMapper;
import com.checkin.service.HistoryLoginService;
import com.checkin.utils.ExportUtils;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HistoryLoginServiceImpl implements HistoryLoginService {
    @Autowired
    private HistoryLoginMapper historyLoginMapper;

    @Override
    public BaseResponse getListHistoryLogin(HistoryLoginRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        List<HistoryLoginResponse> list = historyLoginMapper.listHistoryLogin(request);
        baseResponse.setTotalRecords(historyLoginMapper.countHistoryLogin(request).size());
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
    public File exportHistoryLoginDetail(HistoryLoginRequest request) {
        File file = null;
        try {
            file = File.createTempFile("out", ".tmp");
            file.deleteOnExit();
            Resource resource = new ClassPathResource("templates/login_detail.jasper");
            FileOutputStream fos = new FileOutputStream(file);
            InputStream inputStream = resource.getInputStream();
            System.out.println("inputStream"+inputStream);
            List<HistoryLoginResponse> list = historyLoginMapper.listHistoryLoginDetail(request);
            Map<String, Object> parameters = new HashMap<>();
            ExportUtils.exportReport(inputStream, fos, parameters, list, "pdf");


        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return file;
    }

    @Override
    public File exportHistoryLogin(HistoryLoginRequest request) {
        File file = null;
        try {
            file = File.createTempFile("out", ".tmp");
            file.deleteOnExit();
            Resource resource = new ClassPathResource("templates/history_login.jasper");
           FileOutputStream fos = new FileOutputStream(file);
                 InputStream inputStream = resource.getInputStream();
                System.out.println("inputStream"+inputStream);
                List<HistoryLoginResponse> list = historyLoginMapper.listHistoryLogin(request);
                Map<String, Object> parameters = new HashMap<>();
                ExportUtils.exportReport(inputStream, fos, parameters, list, "pdf");


        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return file;
    }
}
