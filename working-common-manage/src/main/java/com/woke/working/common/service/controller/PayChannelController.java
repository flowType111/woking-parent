package com.woke.working.common.service.controller;

import com.woke.working.api.common.PayChannelApi;
import com.woke.working.common.service.service.PayChannelService;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PayChannelController implements PayChannelApi {

    @Autowired
    private PayChannelService payChannelService;

    @Override
    public ResponseVo getChannel(@RequestParam("paymentMethod") String paymentMethod, HttpServletRequest request) {
        return payChannelService.getChannel(paymentMethod, request);
    }
}
