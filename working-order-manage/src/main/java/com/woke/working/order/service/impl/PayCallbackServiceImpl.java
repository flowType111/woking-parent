package com.woke.working.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.order.PayCallbackDTO;
import com.woke.working.common.enumeration.order.PayStatusEnum;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.order.dao.PayOrderDao;
import com.woke.working.order.entity.PayOrder;
import com.woke.working.order.service.PayCallbackService;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class PayCallbackServiceImpl implements PayCallbackService {

    @Autowired
    private PayOrderDao payOrderDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo payCallback(PayCallbackDTO payCallbackDTO) {
        log.info("支付成功回调信息：{}", JSON.toJSONString(payCallbackDTO));
        // 查询待付款订单
        PayOrder payOrder = payOrderDao.selectOne(new LambdaQueryWrapper<PayOrder>()
                .eq(PayOrder::getOrderStatus, PayStatusEnum.OBLIGATION.getStatusCode())
                .eq(PayOrder::getQrCodeId,payCallbackDTO.getQrCodeId()));
        if (Objects.isNull(payOrder)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_ORDER_PAY_STATUS_NOT_EXIST);
        }
        payOrder.setOrderAmount(payCallbackDTO.getAmount());
        payOrder.setOrderStatus(PayStatusEnum.PAID.getStatusCode());
        return ResponseVo.success(payOrderDao.updateById(payOrder));
    }
}
