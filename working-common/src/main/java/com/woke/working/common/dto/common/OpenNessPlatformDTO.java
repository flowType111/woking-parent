package com.woke.working.common.dto.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OpenNessPlatformDTO extends AccessTokenAuthDTO {

    // 渠道
    @NotNull(message = "渠道不能为空")
    private Integer channelType;

    // 回调url
    private String callbackUrl;
}
