package com.woke.working.common.dto.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InterFaceConfigDTO {

    @NotNull(message = "上级id不能为空")
    private String interfaceAuthId;

    @NotNull(message = "请配置当前白名单账号可访问的接口Code")
    private String interfaceCode;
}
