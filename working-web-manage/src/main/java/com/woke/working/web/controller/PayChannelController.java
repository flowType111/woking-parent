package com.woke.working.web.controller;

import com.woke.working.api.web.PayChannelApi;
import com.woke.working.common.dto.web.PayChannelRequestParam;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.service.PayChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PayChannelController implements PayChannelApi {

    @Autowired
    PayChannelService payChannelService;

    @Override
    public ResponseVo payChannel(@RequestBody @Valid PayChannelRequestParam params) {
        return payChannelService.payChannel(params);
    }
}
