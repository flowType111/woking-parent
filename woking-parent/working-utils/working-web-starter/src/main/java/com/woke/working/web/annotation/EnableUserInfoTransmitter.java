package com.woke.working.web.annotation;

import com.woke.working.web.config.EnableUserInfoTransmitterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({EnableUserInfoTransmitterAutoConfiguration.class})
public @interface EnableUserInfoTransmitter {
}
