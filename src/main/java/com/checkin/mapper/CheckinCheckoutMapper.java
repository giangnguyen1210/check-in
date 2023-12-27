package com.checkin.mapper;

import com.checkin.dto.request.CheckinCheckoutRequest;
import com.checkin.dto.response.CheckinCheckoutResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckinCheckoutMapper {
    List<CheckinCheckoutResponse> listCheckinCheckout(CheckinCheckoutRequest request);
    List<CheckinCheckoutResponse> listCheckinCheckoutDetail(CheckinCheckoutRequest request);
}
