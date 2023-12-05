package com.woke.working.user;

import com.woke.working.redis.annotation.EnableRedis;
import com.woke.working.web.annotation.EnableUserInfoTransmitter;
import com.woke.working.web.annotation.EnableVerificationCode;
import com.woke.working.web.annotation.EnableWebUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.woke.working.user.dao")
@EnableUserInfoTransmitter
@EnableRedis
@EnableWebUtil
@EnableVerificationCode
public class WorkingUserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingUserManageApplication.class, args);
    }

}
