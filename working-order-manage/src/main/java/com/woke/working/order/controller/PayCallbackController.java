package com.woke.working.order.controller;

import com.woke.working.api.order.PayCallbackApi;
import com.woke.working.common.dto.order.PayCallbackDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.order.service.PayCallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PayCallbackController implements PayCallbackApi {

    @Autowired
    private PayCallbackService payCallbackService;

    @Override
    public ResponseVo payCallback(@RequestBody @Valid PayCallbackDTO payCallbackDTO) {
        return payCallbackService.payCallback(payCallbackDTO);
    }
}
