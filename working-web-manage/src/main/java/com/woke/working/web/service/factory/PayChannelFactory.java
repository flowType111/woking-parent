package com.woke.working.web.service.factory;

import com.alibaba.fastjson.JSONObject;
import com.woke.working.common.enumeration.web.PayChannelEnum;
import com.woke.working.web.config.PayChannelAbstractExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class PayChannelFactory {
    @Autowired
    private Map<String, PayChannelAbstractExecutor> executorMap;

    private static final Map<String, String> beanNames = new ConcurrentHashMap<>();

    static {
        PayChannelEnum[] payChannelStrategyEnums = PayChannelEnum.values();
        for (PayChannelEnum payChannelEnum : payChannelStrategyEnums) {
            beanNames.put(payChannelEnum.getPayType(), payChannelEnum.getBeanName());
        }
    }

    public void execute(String payType) throws ParseException {
        String beanName = beanNames.get(payType);
        if (StringUtils.isEmpty(beanName)) {
            return;
        }
        PayChannelAbstractExecutor executor = executorMap.get(beanName);
        if (executor == null) {
            return;
        }
        executor.execute();
    }
}
