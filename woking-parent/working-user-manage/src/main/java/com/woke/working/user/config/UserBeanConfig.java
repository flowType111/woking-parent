package com.woke.working.user.config;

import com.woke.working.common.util.RSAEncrypt;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfig {

    @Bean
    @ConfigurationProperties("rsa.key")
    public RSAEncrypt rsaEncrypt() {
        return new RSAEncrypt();
    }
}
