package com.woke.working.web.service;

import com.woke.working.common.dto.web.PayChannelRequestParam;
import com.woke.working.common.vo.ResponseVo;

public interface PayChannelService {

    ResponseVo payChannel(PayChannelRequestParam params);
}
