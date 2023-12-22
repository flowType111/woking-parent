package com.woke.working.order.service.factory;

import com.woke.working.common.enumeration.order.MessageEnum;
import com.woke.working.order.config.MessageAbstractExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class MessageFactory {
    @Autowired
    private Map<String, MessageAbstractExecutor> executorMap;

    private static final Map<String, String> beanNames = new ConcurrentHashMap<>();

    static {
        MessageEnum[] messageEnumsStrategyEnums = MessageEnum.values();
        for (MessageEnum messageEnum : messageEnumsStrategyEnums) {
            beanNames.put(messageEnum.getPayType(), messageEnum.getBeanName());
        }
    }

    public void execute(String payType,String message) {
        String beanName = beanNames.get(payType);
        if (StringUtils.isEmpty(beanName)) {
            return;
        }
        MessageAbstractExecutor executor = executorMap.get(beanName);
        if (executor == null) {
            return;
        }
        executor.execute(message);
    }
}
