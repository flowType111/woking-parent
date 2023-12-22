package com.woke.working.common.dto.order;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PayCallbackDTO {


    @NotNull(message = "支付码id不能为空")
    private String qrCodeId;

    @NotNull(message = "支付金额不能为空")
    private BigDecimal amount;

    private Integer payStatus;
}
