package com.woke.working.common.vo.user;

import lombok.Data;

import java.util.List;

@Data
public class SystemMenuTreeVo {

    private String id;

    private String menuName;

    private String menuCode;

    private String parentId;

    private String parentCode;

    private Integer nodeType;

    private Integer sort;

    private Integer level;

    private List<SystemMenuTreeVo> cheldrenList;
}
