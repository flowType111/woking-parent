package com.woke.woking.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.woke.working.api.user.feign.UserAuthFeign;
import com.woke.working.common.constant.user.UserConstant;
import com.woke.working.common.dto.user.UserTokenDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.reactive.result.view.RequestContext;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class TokenGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private UserAuthFeign userAuthFeign;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1、获取请求对象  ServerHttpRequest
        ServerHttpRequest request = exchange.getRequest();
        //2、获取请求的资源路径
        String path = request.getURI().getPath();
        MultiValueMap<String,String> params = request.getQueryParams();
        //2.获取参数中的authorization参数
        String value = params.getFirst(UserConstant.TOKEN);
        // 没有token直接执行
        if (StringUtils.isEmpty(value)){
            return chain.filter(exchange);
        }
        ResponseVo responseVo = userAuthFeign.getAuthInfo(new UserTokenDTO(value));
        if (responseVo != null && responseVo.isSuccess()) {
            Map responseMap = (Map) responseVo.getData();
            try {
                String message = new String(Base64Utils.encode(JSONObject.toJSONString(responseMap.get(UserConstant.USER_ACCOUNT)).
                        getBytes("utf-8")), "utf-8");
                request = exchange.getRequest().mutate().header(UserConstant.USER_ACCOUNT_INFO, message).build();
            } catch (UnsupportedEncodingException e) {
                log.error("网关认证失败",e);
            }
        }
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
