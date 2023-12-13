package com.woke.working.common;

import com.woke.working.redis.annotation.EnableRedis;
import com.woke.working.web.annotation.EnableUserInfoTransmitter;
import com.woke.working.web.annotation.EnableWebUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.woke.working.common.dao")
@EnableUserInfoTransmitter
@EnableRedis
@EnableWebUtil
public class WorkingCommonManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingCommonManageApplication.class, args);
    }

}
