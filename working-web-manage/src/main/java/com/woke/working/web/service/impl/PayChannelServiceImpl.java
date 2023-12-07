package com.woke.working.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.web.PayChannelRequestParam;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import com.woke.working.web.service.PayChannelService;
import com.woke.working.web.service.factory.PayChannelFactory;
import com.woke.working.web.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PayChannelServiceImpl implements PayChannelService {

    @Autowired
    private PayChannelFactory payChannelFactory;

    @Override
    public ResponseVo payChannel(PayChannelRequestParam params) {
        System.out.println("获取到参数信息"+ params);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JSONObject jsonObject = JSONObject.parseObject(objectMapper.writeValueAsString(params));

            // 组装参数
            Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("blockChannelDTO",jsonObject);
            ThreadLocalUtil.setLocalVar(paramsMap);

            payChannelFactory.execute(jsonObject.getString("payType"));
        } catch (Exception e) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_WEB_PAY_CHANNEL_ERROR);
        } finally {
            ThreadLocalUtil.removeLocalVar();
        }
        return ResponseVo.success();
    }
}
