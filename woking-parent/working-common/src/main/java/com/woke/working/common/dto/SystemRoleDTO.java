package com.woke.working.common.dto;

import com.woke.working.common.valid.ValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SystemRoleDTO {

    @NotNull(message = "id不能为空", groups = {ValidGroup.Update.class})
    private String id;

    @NotNull(message = "角色code不能为空", groups = {ValidGroup.Insert.class})
    private String roleCode;

    @NotNull(message = "角色名称不能为空", groups = {ValidGroup.Insert.class})
    private String roleName;
}
