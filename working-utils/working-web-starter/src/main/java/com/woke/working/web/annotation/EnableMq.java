package com.woke.working.web.annotation;

import com.woke.working.web.config.EnableMqConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({EnableMqConfiguration.class})
public @interface EnableMq {
}
