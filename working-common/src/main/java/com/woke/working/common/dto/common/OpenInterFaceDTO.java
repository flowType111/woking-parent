package com.woke.working.common.dto.common;

import com.woke.working.common.valid.ValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OpenInterFaceDTO {

    @NotNull(message = "对外接口code不能为空", groups = {ValidGroup.Update.class})
    private String id;

    @NotNull(message = "对外接口code不能为空", groups = {ValidGroup.Insert.class})
    private String interFaceCode;

    @NotNull(message = "请输入接口名称", groups = {ValidGroup.Insert.class})
    private String interFaceName;

    @NotNull(message = "请输入描述", groups = {ValidGroup.Insert.class})
    private String interFaceDescribe;
}
