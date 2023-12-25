package com.woke.working.web.aspect;

import com.woke.working.api.common.InterFaceAuthApi;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.annotation.VerifySignature;
import com.woke.working.common.dto.common.AccessTokenAuthDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Slf4j
@Order(3)
public class VerifySignatureAspect {

    @Autowired
    private InterFaceAuthApi interFaceAuthApi;

    @Pointcut("@within(com.woke.working.common.annotation.VerifySignature) " +
            "|| @annotation(com.woke.working.common.annotation.VerifySignature)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            // 检验当前用户token
            if (arg instanceof AccessTokenAuthDTO) {
                AccessTokenAuthDTO accessTokenAuthDTO = (AccessTokenAuthDTO) arg;
                ResponseVo responseVo = interFaceAuthApi.interFaceAuth(accessTokenAuthDTO);
                if (responseVo != null && responseVo.isSuccess()) {
                    List<String> code = (List<String>) responseVo.getData();
                    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                    Method method = methodSignature.getMethod();
                    VerifySignature verifySignature = method.getAnnotation(VerifySignature.class);
                    if (verifySignature != null) {
                        if (!CollectionUtils.isEmpty(code)) {
                            if (code.contains(verifySignature.apiCode())) {
                                log.info("----- VerifySignature check pass ----");
                                return;
                            }
                        }
                    }
                }
            }
        }
        throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_NO_API_PERMISSIONS);
    }
}
