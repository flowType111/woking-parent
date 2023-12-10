package com.woke.working.web;

import com.woke.working.web.annotation.EnableMq;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableMq
public class WorkingWebManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingWebManageApplication.class, args);
    }


    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
