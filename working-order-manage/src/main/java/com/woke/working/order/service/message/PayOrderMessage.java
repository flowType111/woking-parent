package com.woke.working.order.service.message;

import com.alibaba.fastjson.JSON;
import com.woke.working.common.constant.MqKeyConstant;
import com.woke.working.common.dto.web.PayOrderDTO;
import com.woke.working.order.service.PayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付订单信息
 */
@Service
@Slf4j
public class PayOrderMessage {

    @Autowired
    private PayOrderService payOrderService;

    @RabbitListener(queues = MqKeyConstant.MqTopic.orderTopic)
    @RabbitHandler
    public void payOrderMessage(String message) {
        log.info("订单信息中心：{}", message);
        PayOrderDTO payOrderDTO = JSON.parseObject(message, PayOrderDTO.class);
        payOrderService.addPayOrder(payOrderDTO);
    }
}
