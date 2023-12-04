package com.woke.working.web.config;

import com.woke.working.web.aspect.VerificationCodeAspect;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableFeignClients(basePackages = {"com.woke.working.api.common.feign"})
public class EnableVerificationAutoConfiguration {
    @Bean
    public VerificationCodeAspect verificationCodeAspect() {
        return new VerificationCodeAspect();
    }
}
