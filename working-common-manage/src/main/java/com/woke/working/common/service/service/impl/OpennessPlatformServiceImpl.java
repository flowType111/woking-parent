package com.woke.working.common.service.service.impl;

import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.common.OpenNessPlatformDTO;
import com.woke.working.common.service.service.OpennessPlatformService;
import com.woke.working.common.service.service.factory.PayChannelFactory;
import com.woke.working.common.service.util.ThreadLocalUtil;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OpennessPlatformServiceImpl implements OpennessPlatformService {

    @Autowired
    private PayChannelFactory payChannelFactory;

    @Override
    public ResponseVo placeOrder(OpenNessPlatformDTO openNessPlatformDTO) {
        log.info("调用下单接口：{}", openNessPlatformDTO);
        ResponseVo responseVo = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("openNessPlatformDTO", openNessPlatformDTO);
            ThreadLocalUtil.setLocalVar(params);
            responseVo = payChannelFactory.execute(openNessPlatformDTO.getChannelType());
        } catch (Exception e) {
            log.error("获取订单失败：{}", e);
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_WEB_PAY_CHANNEL_ERROR);
        } finally {
            ThreadLocalUtil.removeLocalVar();
        }
        return responseVo;
    }
}
