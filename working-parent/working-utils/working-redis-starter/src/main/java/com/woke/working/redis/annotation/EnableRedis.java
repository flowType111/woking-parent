package com.woke.working.redis.annotation;

import com.woke.working.redis.config.RedisConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RedisConfiguration.class})
@ComponentScan("com.woke.working.redis.config")
public @interface EnableRedis {

}
