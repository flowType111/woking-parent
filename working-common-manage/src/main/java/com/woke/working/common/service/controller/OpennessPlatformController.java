package com.woke.working.common.service.controller;

import com.woke.working.api.common.OpennessPlatformApi;
import com.woke.working.common.annotation.VerifySignature;
import com.woke.working.common.dto.common.OpenNessPlatformDTO;
import com.woke.working.common.service.service.OpennessPlatformService;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OpennessPlatformController implements OpennessPlatformApi {


    @Autowired
    private OpennessPlatformService opennessPlatformService;

    @VerifySignature(apiCode = "placeOrder")
    @Override
    public ResponseVo placeOrder(@RequestBody @Valid OpenNessPlatformDTO openNessPlatformDTO) {
        return opennessPlatformService.placeOrder(openNessPlatformDTO);
    }
}
