package com.woke.working.api.order;

import com.woke.working.common.dto.order.PayCallbackDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pay/callback")
public interface PayCallbackApi {

    @PostMapping("/payCallback")
    ResponseVo payCallback(PayCallbackDTO payCallbackDTO);
}
