package com.woke.working.common.dto;

import com.woke.working.common.constant.VaildateMessageConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginDTO {

    @NotNull(message = VaildateMessageConstant.user.ACCOUNT_IS_NOT_NULL)
    private String accountNo;

    @NotNull(message = VaildateMessageConstant.user.PASSWORD_IS_NOT_NULL)
    private String password;
}