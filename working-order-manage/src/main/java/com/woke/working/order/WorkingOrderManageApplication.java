package com.woke.working.order;

import com.woke.working.redis.annotation.EnableRedis;
import com.woke.working.web.annotation.EnableUserInfoTransmitter;
import com.woke.working.web.annotation.EnableWebUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.woke.working.order.dao")
@EnableUserInfoTransmitter
@EnableRedis
@EnableWebUtil
public class WorkingOrderManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingOrderManageApplication.class, args);
    }

}
