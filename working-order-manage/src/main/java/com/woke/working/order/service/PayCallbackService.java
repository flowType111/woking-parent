package com.woke.working.order.service;

import com.woke.working.common.dto.order.PayCallbackDTO;
import com.woke.working.common.vo.ResponseVo;

public interface PayCallbackService {

    ResponseVo payCallback(PayCallbackDTO payCallbackDTO);
}
