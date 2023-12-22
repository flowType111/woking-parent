package com.woke.working.web.service.factory;

import com.alibaba.fastjson.JSON;
import com.woke.working.common.constant.MqKeyConstant;
import com.woke.working.common.dto.MessageDTO;
import com.woke.working.common.dto.web.QrcodeChannelDTO;
import com.woke.working.common.enumeration.web.PayChannelEnum;
import com.woke.working.web.config.PayChannelAbstractExecutor;
import com.woke.working.web.send.MqSenderService;
import com.woke.working.web.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qrCodeChannelService")
@Slf4j
public class QrCodeChannelService extends PayChannelAbstractExecutor {

    @Autowired
    private MqSenderService mqSenderService;

    @Override
    public void execute() {
        QrcodeChannelDTO qrcodeChannelDTO = JSON.parseObject(JSON.toJSONString(ThreadLocalUtil.getLocalVar().get("paramsMap")), QrcodeChannelDTO.class);
        //发送mq信息生成订单号
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(JSON.toJSONString(qrcodeChannelDTO));
        messageDTO.setMessageType(PayChannelEnum.QR_CODE_CHANNEL.getPayType());
        mqSenderService.sendMessage(MqKeyConstant.MqExchange.exchange, true, MqKeyConstant.RoutingKey.routingKey, JSON.toJSONString(messageDTO));
    }
}
