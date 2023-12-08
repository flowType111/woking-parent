package com.woke.working.web.send.impl;

import com.woke.working.web.send.MqSenderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

public class MqSenderServiceImpl implements MqSenderService {

    private RabbitTemplate rabbitTemplate;

    public MqSenderServiceImpl(RabbitTemplate rabbitTemplate, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
    }

    @Override
    public void sendMessage(String exchange, Boolean transacted, String routingKey, Object message) {
        rabbitTemplate.setChannelTransacted(transacted);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
