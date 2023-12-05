package com.woke.working.web.annotation;

import com.woke.working.web.config.WebUtilConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({WebUtilConfiguration.class})
@ComponentScan({"com.woke.working.web.config"})
public @interface EnableWebUtil {
}
