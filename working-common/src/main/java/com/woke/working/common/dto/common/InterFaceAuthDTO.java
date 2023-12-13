package com.woke.working.common.dto.common;

import com.woke.working.common.valid.ValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class InterFaceAuthDTO {

    @NotNull(message = "id不能为空", groups = {ValidGroup.Update.class})
    private String id;

    @NotNull(message = "白名单账号不能为空", groups = {ValidGroup.Insert.class})
    private String accessKey;

    @NotNull(message = "白名单密码不能为空", groups = {ValidGroup.Insert.class})
    private String encryptionKey;

    private List<InterFaceConfigDTO> openApiList;
}
