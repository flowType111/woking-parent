package com.woke.working.common.annotation;

import java.lang.annotation.*;

/**
 * 第三方接口验签
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface VerifySignature {
}
