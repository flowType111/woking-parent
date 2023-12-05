package com.woke.woking.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.woke.working.api.user.feign"})
public class WorkingGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingGatewayApplication.class, args);
    }

}