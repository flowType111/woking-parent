package com.woke.working.order.service.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.woke.working.common.constant.RedisKeyConstant;
import com.woke.working.common.enumeration.order.PayStatusEnum;
import com.woke.working.common.enumeration.web.PayChannelEnum;
import com.woke.working.order.config.MessageAbstractExecutor;
import com.woke.working.order.dao.PayOrderDao;
import com.woke.working.order.entity.PayOrder;
import com.woke.working.order.util.HttpUtil;
import com.woke.working.order.util.OrderUtil;
import com.woke.working.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 二维码消息处理服务类
 */
@Service("qrCodeMessageService")
@Slf4j
public class QrCodeMessageFactory extends MessageAbstractExecutor {

    @Autowired
    private PayOrderDao payOrderDao;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${addRedEnvelopeTime}")
    private String addRedEnvelopeTime;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(String message) {
        log.info("二维码mq消息消费：{}", JSON.parseObject(message));
        JSONObject jsonObject = JSON.parseObject(message);
        if (jsonObject == null && jsonObject.size() < 0) {
            log.info("二维码信息为空：{}", jsonObject);
            return;
        }
        String orderNo = OrderUtil.getOrderNumber();
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderStatus(PayStatusEnum.OBLIGATION.getStatusCode());
        payOrder.setQrCodeId(jsonObject.getString("qrCodeId"));
        payOrder.setOrderNo(orderNo);
        payOrder.setPayChannelEnum(Integer.parseInt(PayChannelEnum.QR_CODE_CHANNEL.getPayType()));
        payOrderDao.insert(payOrder);
        redisUtil.setObject(RedisKeyConstant.ORDER_TIME_KEY + jsonObject.getString("qrCodeId"), payOrder, 180L);

        // 调用下级监控
        JSONObject params = new JSONObject();
        jsonObject.put("qcodeNo", jsonObject.getString("qrCodeId"));
        jsonObject.put("type", 0);
        jsonObject.put("name", "");
        String result = HttpUtil.doPost(addRedEnvelopeTime, params);
        log.info("调用二维码端接口：{}", result);
    }
}
