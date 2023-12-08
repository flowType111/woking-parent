package com.woke.working.web;

import com.woke.working.web.annotation.EnableMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMq
public class WorkingWebManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingWebManageApplication.class, args);
    }

}
