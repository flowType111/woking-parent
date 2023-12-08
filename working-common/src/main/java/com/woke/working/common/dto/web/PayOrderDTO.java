package com.woke.working.common.dto.web;

import com.woke.working.common.enumeration.order.PayStatusEnum;
import com.woke.working.common.enumeration.web.PayChannelEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PayOrderDTO {

    private String orderNo;

    private BigDecimal orderAmount;

    private PayStatusEnum orderStatus;

    private PayChannelEnum payChannelEnum;
}
