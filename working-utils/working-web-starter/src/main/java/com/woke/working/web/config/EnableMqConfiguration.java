package com.woke.working.web.config;

import com.woke.working.web.send.MqSenderService;
import com.woke.working.web.send.impl.MqSenderServiceImpl;
import com.woke.working.web.util.MqUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

@Slf4j
public class EnableMqConfiguration {

    @Bean
    public MqUtils mqUtils() {
        return new MqUtils();
    }

    @Bean
    public MqSenderService mqSenderService(RabbitTemplate rabbitTemplate, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        return new MqSenderServiceImpl(rabbitTemplate, jackson2JsonMessageConverter);
    }
}
