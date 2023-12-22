package com.woke.working.web.service.factory;

import com.alibaba.fastjson.JSON;
import com.woke.working.common.constant.MqKeyConstant;
import com.woke.working.common.dto.web.BlockChannelDTO;
import com.woke.working.common.dto.web.PayOrderDTO;
import com.woke.working.common.enumeration.order.PayStatusEnum;
import com.woke.working.common.enumeration.web.PayChannelEnum;
import com.woke.working.web.config.PayChannelAbstractExecutor;
import com.woke.working.web.send.MqSenderService;
import com.woke.working.web.util.OrderUtil;
import com.woke.working.web.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * 卡密渠道服务类
 */
@Service("blockChannelService")
public class BlockChannelService extends PayChannelAbstractExecutor {

    @Autowired
    private MqSenderService mqSenderService;

    @Override
    public void execute() {
        BlockChannelDTO blockChannelDTO = JSON.parseObject(JSON.toJSONString(ThreadLocalUtil.getLocalVar().get("paramsMap")), BlockChannelDTO.class);
        // 生成订单号
        String orderCode = OrderUtil.getOrderNumber();
        PayOrderDTO payOrderDTO = PayOrderDTO.builder()
                .orderNo(orderCode)
                .payChannelEnum(Integer.parseInt(PayChannelEnum.BLOCK_CHANNEL.getPayType()))
                .orderStatus(PayStatusEnum.OBLIGATION.getStatusCode())
                .build();
        // 发送mq到订单服务
        mqSenderService.sendMessage(MqKeyConstant.MqExchange.exchange, true, MqKeyConstant.RoutingKey.routingKey, JSON.toJSONString(payOrderDTO));
    }
}
