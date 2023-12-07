package com.woke.working.api.web;

import com.woke.working.common.dto.web.PayChannelRequestParam;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * 渠道提交时生成订单号
 *
 */
@RequestMapping("/pay/channel")
public interface PayChannelApi {

    @RequestMapping("/submitPayWay")
    ResponseVo payChannel(PayChannelRequestParam params);
}
