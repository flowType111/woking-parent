package com.woke.working.common.service;

import com.woke.working.db.config.annotation.EnableDBField;
import com.woke.working.redis.annotation.EnableRedis;
import com.woke.working.web.annotation.EnableMq;
import com.woke.working.web.annotation.EnableUserInfoTransmitter;
import com.woke.working.web.annotation.EnableVerifySignature;
import com.woke.working.web.annotation.EnableWebUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.woke.working.common.service.dao")
@EnableUserInfoTransmitter
@EnableRedis
@EnableWebUtil
@EnableDBField
@EnableMq
@EnableVerifySignature
public class WorkingCommonManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingCommonManageApplication.class, args);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
