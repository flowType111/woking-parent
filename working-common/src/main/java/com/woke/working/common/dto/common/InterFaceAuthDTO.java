package com.woke.working.common.dto.common;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class InterFaceAuthDTO {

    @NotNull(message = "白名单账号不能为空")
    private String accessKey;

    @NotNull(message = "白名单密码不能为空")
    private String encryptionKey;

    private List<String> openApiList;
}
