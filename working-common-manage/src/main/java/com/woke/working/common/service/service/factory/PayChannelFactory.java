package com.woke.working.common.service.service.factory;

import com.woke.working.common.enumeration.web.PayChannelEnum;
import com.woke.working.common.service.config.PayChannelAbstractExecutor;
import com.woke.working.common.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class PayChannelFactory {
    @Autowired
    private Map<String, PayChannelAbstractExecutor> executorMap;

    private static final Map<Integer, String> beanNames = new ConcurrentHashMap<>();

    static {
        PayChannelEnum[] payChannelStrategyEnums = PayChannelEnum.values();
        for (PayChannelEnum payChannelEnum : payChannelStrategyEnums) {
            beanNames.put(payChannelEnum.getPayType(), payChannelEnum.getBeanName());
        }
    }

    public ResponseVo execute(Integer payType) {
        String beanName = beanNames.get(payType);
        if (StringUtils.isEmpty(beanName)) {
            return ResponseVo.fail("没有渠道bean");
        }
        PayChannelAbstractExecutor executor = executorMap.get(beanName);
        if (executor == null) {
            return ResponseVo.fail("现在渠道失败");
        }
        return executor.execute();
    }
}
