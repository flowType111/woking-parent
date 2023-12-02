package com.woke.working.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.woke.working.user.dao")
/*@EnableRedis*/
public class WorkingUserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingUserManageApplication.class, args);
    }

}
