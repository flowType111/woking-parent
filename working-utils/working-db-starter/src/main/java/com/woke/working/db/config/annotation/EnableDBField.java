package com.woke.working.db.config.annotation;

import com.woke.working.db.config.config.MyMetaObjectHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({MyMetaObjectHandler.class})
public @interface EnableDBField {
}
