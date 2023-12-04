package com.woke.working.common.annotation;

import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * 权限限制
 * 注解标注的方法在执行前应被检查当前用户的权限代码列表中是否有value值的代码
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AuthorityLimit {

    @NotNull String[] value();
}
