package com.woke.working.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.woke.working.common.bo.UserInfoBO;
import com.woke.working.common.constant.user.UserConstant;
import com.woke.working.common.util.user.UserInfoContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

@Slf4j
public class TransmitUserInfoFeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        UserInfoBO user = UserInfoContext.getLoginUser();
        if (user != null) {
            try {
                String message = new String(Base64Utils.encode(JSONObject.toJSONString(user)
                        .getBytes("utf-8")), "utf-8");
                requestTemplate.header(UserConstant.USER_ACCOUNT_INFO, message);
            } catch (Exception e) {
                log.error("TransmitUserInfoFeignClientInterceptor：{}", e);
            }
        }
    }
}
