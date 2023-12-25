package com.woke.working.api.common;

import com.woke.working.common.dto.common.OpenNessPlatformDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/open/api")
public interface OpennessPlatformApi {

    @PostMapping("/placeOrder")
    ResponseVo placeOrder(OpenNessPlatformDTO openNessPlatformDTO);
}
