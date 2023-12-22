package com.woke.working.common.service.service;

import com.woke.working.common.vo.ResponseVo;

import javax.servlet.http.HttpServletRequest;

public interface PayChannelService {

    ResponseVo getChannel(String paymentMethod, HttpServletRequest request);
}
