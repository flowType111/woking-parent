package com.woke.working.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_menu")
public class SystemMenu {

    @TableField("menu_name")
    private String menuName;

    @TableField("menu_code")
    private String menuCode;

    @TableField("parent_id")
    private String parentId;

    @TableField("node_type")
    private Integer nodeType;

    @TableField("sort")
    private Integer sort;

    @TableField("level")
    private Integer level;
}
