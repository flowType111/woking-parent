package com.woke.working.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.woke.working.common.bo.UserInfoBO;
import com.woke.working.common.constant.user.UserConstant;
import com.woke.working.common.util.user.UserInfoContext;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@NoArgsConstructor
@Slf4j
public class TransmitUserInfoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserInfoContext.removeCurrentInfo();
        this.initUserInfo((HttpServletRequest) servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void initUserInfo(HttpServletRequest request) {
        String userJson = request.getHeader(UserConstant.USER_ACCOUNT_INFO);
        if (StringUtils.isNotBlank(userJson)) {
            try {
                UserInfoBO userInfo = JSONObject.parseObject(new String(Base64Utils.decode(userJson.getBytes()),
                        "utf-8"), UserInfoBO.class);
                //将UserInfo放入上下文中
                UserInfoContext.setLoginUser(userInfo);
            } catch (Exception e) {
                log.error("放用户信息到上下文失败：{}", e);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
