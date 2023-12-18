package com.woke.working.web.aspect;

import com.woke.working.api.common.ImageCodeApi;
import com.woke.working.api.common.feign.ImageCodeFeign;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.user.CheckImageDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;


@Aspect
@Order(2)
public class VerificationCodeAspect {

    @Autowired
    private ImageCodeFeign imageCodeFeign;

    @Pointcut("@within(com.woke.working.common.annotation.VerificationCode) " +
            "|| @annotation(com.woke.working.common.annotation.VerificationCode)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof CheckImageDTO) {
                CheckImageDTO checkImageDTO = (CheckImageDTO) arg;
                ResponseVo responseVo = imageCodeFeign.verifyCode(checkImageDTO);
                if (!Boolean.getBoolean(String.valueOf(responseVo.getData()))) {
                    throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_CHECK_CODE_ERROR);
                }
            }
        }
    }
}
