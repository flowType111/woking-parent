package com.woke.working.common.dto.web;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlockChannelDTO extends PayChannelRequestParam {

    /**
     * 卡密
     */
    private String blockPassword;
}
