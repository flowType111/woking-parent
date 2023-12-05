package com.woke.working.common.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CheckImageDTO {

    @NotEmpty(message = "请输入验证码")
    private String imageCode;

    @NotEmpty(message = "随机字符串不能为空")
    private String randomCode;
}
