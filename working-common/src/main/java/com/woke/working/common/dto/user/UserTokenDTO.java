package com.woke.working.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDTO {

    @NotNull(message = "登陆token不能为空")
    private String token;
}
