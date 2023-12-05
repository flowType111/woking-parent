package com.woke.working.web.aspect;

import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.annotation.AuthorityLimit;
import com.woke.working.common.bo.UserInfoBO;
import com.woke.working.common.util.user.UserInfoContext;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;

@Aspect
@Order(1)
@Slf4j
public class JurisdictionAspect {

    @Before("@annotation(com.woke.working.common.annotation.Authority ) || " +
            "@annotation(com.woke.working.common.annotation.AuthorityLimit)")
    public void doBefore(JoinPoint joinPoint) {
        UserInfoBO user = UserInfoContext.getLoginUser();
        if (user == null) {
            throw new BusinessErrorException(BusinessMsgEnum.NO_AUTH);
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AuthorityLimit authorityLimit = method.getAnnotation(AuthorityLimit.class);
        if (authorityLimit != null) {
            this.checkJurisdiction(user, authorityLimit.value());
        }
    }

    private void checkJurisdiction(UserInfoBO userInfoBO, String[] jurisdictionCodes) {
        if (!CollectionUtils.isEmpty(userInfoBO.getJurisdictionCodeList())) {
            for (String jurisdictionCode : jurisdictionCodes) {
                if (userInfoBO.getJurisdictionCodeList().contains(jurisdictionCode)) {
                    log.info("----- authority check pass ----");
                    return;
                }
            }
        }
        throw new BusinessErrorException(BusinessMsgEnum.AUTHORITY_ERROR);
    }
}
