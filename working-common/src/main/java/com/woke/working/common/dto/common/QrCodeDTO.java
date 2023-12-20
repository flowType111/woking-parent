package com.woke.working.common.dto.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QrCodeDTO {

    @NotNull(message = "二维码id不能为空")
    private String qrCodeId;

    @NotNull(message = "二维码图片不能为空")
    private String qrCodePath;

    @NotNull(message = "二维码状态")
    private Integer qrCodeStatus;
}
