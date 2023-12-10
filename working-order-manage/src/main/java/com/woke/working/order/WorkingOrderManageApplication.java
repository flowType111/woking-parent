package com.woke.working.order;

import com.woke.working.redis.annotation.EnableRedis;
import com.woke.working.web.annotation.EnableMq;
import com.woke.working.web.annotation.EnableUserInfoTransmitter;
import com.woke.working.web.annotation.EnableWebUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.woke.working.order.dao")
@EnableUserInfoTransmitter
@EnableRedis
@EnableWebUtil
@EnableMq
public class WorkingOrderManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingOrderManageApplication.class, args);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
