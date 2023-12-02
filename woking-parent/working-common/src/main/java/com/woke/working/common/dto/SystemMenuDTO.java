package com.woke.working.common.dto;

import com.woke.working.common.valid.ValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SystemMenuDTO {

    @NotNull(message = "id不能为空", groups = {ValidGroup.Update.class})
    private String id;

    @NotNull(message = "权限点不能为空", groups = {ValidGroup.Insert.class})
    private String menuName;

    @NotNull(message = "权限点code不能为空", groups = {ValidGroup.Insert.class})
    private String menuCode;

    private String parentId;

    @NotNull(message = "请选择权限点类型 1：菜单 2：页面 3：按钮", groups = {ValidGroup.Insert.class})
    private Integer nodeType;

    @NotNull(message = "请选择排序号", groups = {ValidGroup.Insert.class})
    private Integer sort;
}
