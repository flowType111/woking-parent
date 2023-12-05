package com.woke.working.web.annotation;

import com.woke.working.web.config.EnableVerificationAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({EnableVerificationAutoConfiguration.class})
public @interface EnableVerificationCode {
}
