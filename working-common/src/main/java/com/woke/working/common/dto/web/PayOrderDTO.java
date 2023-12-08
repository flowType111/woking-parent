package com.woke.working.common.dto.web;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PayOrderDTO {

    private String orderNo;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payChannelEnum;
}
