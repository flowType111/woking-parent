package com.woke.working.common.service.service.impl;

import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.service.dao.QrCodeDao;
import com.woke.working.common.service.service.PayChannelService;
import com.woke.working.common.service.service.factory.PayChannelFactory;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class PayChannelServiceImpl implements PayChannelService {

    @Autowired
    private PayChannelFactory payChannelFactory;

    @Autowired
    private QrCodeDao qrCodeDao;

    @Override
    public ResponseVo getChannel(String paymentMethod, HttpServletRequest request) {
        ResponseVo responseVo = new ResponseVo();
        try {
            responseVo = payChannelFactory.execute(paymentMethod);
        } catch (Exception e) {
            log.error("提交失败：{}", e);
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_WEB_PAY_CHANNEL_ERROR);
        }
        return responseVo;
    }
}
