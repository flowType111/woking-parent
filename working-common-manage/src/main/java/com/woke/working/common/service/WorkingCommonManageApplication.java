package com.woke.working.common.service;

import com.woke.working.db.config.annotation.EnableDBField;
import com.woke.working.redis.annotation.EnableRedis;
import com.woke.working.web.annotation.EnableUserInfoTransmitter;
import com.woke.working.web.annotation.EnableWebUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.woke.working.common.dao")
@EnableUserInfoTransmitter
@EnableRedis
@EnableWebUtil
@EnableDBField
public class WorkingCommonManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingCommonManageApplication.class, args);
    }

}
