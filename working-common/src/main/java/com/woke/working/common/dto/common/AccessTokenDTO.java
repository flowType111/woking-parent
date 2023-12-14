package com.woke.working.common.dto.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccessTokenDTO {

    @NotNull(message = "accessKey不能为空")
    private String accessKey;

    @NotNull(message = "encryptionKey不能为空")
    private String encryptionKey;
}
