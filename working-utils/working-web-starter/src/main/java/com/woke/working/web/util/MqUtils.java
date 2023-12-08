package com.woke.working.web.util;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

import java.io.IOException;

@Slf4j
public class MqUtils {

    //正常消费掉后通知mq服务器移除此条mq
    public void basicACK(Message message, Channel channel) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("通知服务器移除mq时异常：{}",e);
        }
    }

    //处理异常，mq重回队列
    public void basicNACK(Message message, Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        } catch (IOException e) {
            log.error("通知服务器保留mq时异常：{}",e);
        }
    }
}
