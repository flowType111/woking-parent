package com.woke.working.web.config;

import com.woke.working.web.filter.TransmitUserInfoFilter;
import com.woke.working.web.interceptor.TransmitUserInfoFeignClientInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class EnableUserInfoTransmitterAutoConfiguration {

    @Bean
    public TransmitUserInfoFeignClientInterceptor transmitUserInfo2FeignHttpHeader() {
        log.info("-----init TransmitUserInfoFeignClientInterceptor------");
        return new TransmitUserInfoFeignClientInterceptor();
    }

    @Bean
    public TransmitUserInfoFilter transmitUserInfoFromHttpHeader() {
        log.info("-----init TransmitUserInfoFilter-------");
        return new TransmitUserInfoFilter();
    }
}
