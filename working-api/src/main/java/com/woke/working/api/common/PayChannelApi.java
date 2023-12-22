package com.woke.working.api.common;

import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 根据渠道编码获取该渠道改获取什么支付方式
 *
 */
@RequestMapping("/pay/channel")
public interface PayChannelApi {

    @GetMapping("/getChannel")
    ResponseVo getChannel(String paymentMethod, HttpServletRequest request);
}
