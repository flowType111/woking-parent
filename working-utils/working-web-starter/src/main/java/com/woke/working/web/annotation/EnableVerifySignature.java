package com.woke.working.web.annotation;

import com.woke.working.web.aspect.VerifySignatureAspect;
import com.woke.working.web.config.EnableVerifySignatureConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({EnableVerifySignatureConfiguration.class})
public @interface EnableVerifySignature {
}
