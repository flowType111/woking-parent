package com.woke.working.order.message;

import com.woke.working.common.constant.RedisKeyConstant;
import com.woke.working.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("onMessage --> redis 过期的key是：{}", key);
        try {
            if (key.startsWith(RedisKeyConstant.ORDER_TIME_KEY)) {
                log.info("过期key处理完成：{}", key);
            }
        } catch (Exception e) {
            log.error("处理redis 过期的key异常：{}", e);
        }
    }
}
