package com.woke.working.common.service.config;

import com.woke.working.common.util.RSAEncrypt;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterFaceAuthKeyConfig {

    @Bean
    @ConfigurationProperties("rsa.key")
    public RSAEncrypt rsaEncrypt() {
        return new RSAEncrypt();
    }
}
