package com.woke.working.common.service.service;

import com.woke.working.common.dto.common.OpenNessPlatformDTO;
import com.woke.working.common.vo.ResponseVo;

public interface OpennessPlatformService {

    ResponseVo placeOrder(OpenNessPlatformDTO openNessPlatformDTO);
}
