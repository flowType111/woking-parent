package com.woke.working.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull(message = "用户账号不能为空")
    private String accountNo;

    @NotNull(message = "用户密码不能为空")
    private String password;

    private String nickName;
}
