package com.woke.working.web.config;

import com.woke.working.web.aspect.VerifySignatureAspect;
import org.springframework.context.annotation.Bean;

public class EnableVerifySignatureConfiguration {

    @Bean
    public VerifySignatureAspect verifySignatureAspect(){
        return new VerifySignatureAspect();
    }
}
