package com.woke.working.common.dto.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccessTokenAuthDTO {

    @NotNull(message = "accessKey不能为空")
    private String accessKey;

    @NotNull(message = "token不能为空")
    private String accessToken;
}
