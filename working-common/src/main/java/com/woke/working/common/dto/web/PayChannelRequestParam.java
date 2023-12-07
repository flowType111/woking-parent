package com.woke.working.common.dto.web;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "payType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BlockChannelDTO.class, name = "4")
})
@Data
public class PayChannelRequestParam {

    /**
     * 支付渠道类型
     */
    @NotNull(message = "请选择支付类型")
    private String payType;
}
