package com.woke.working.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SystemMenuDTO {

    @NotNull(message = "权限点不能为空")
    private String menuName;

    @NotNull(message = "权限点code不能为空")
    private String menuCode;

    private String parentId;

    @NotNull(message = "请选择权限点类型 1：菜单 2：页面 3：按钮")
    private Integer nodeType;

    private Integer sort;
}
