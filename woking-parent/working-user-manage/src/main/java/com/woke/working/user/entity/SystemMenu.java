package com.woke.working.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.woke.working.user.config.BaseEntity;
import lombok.Data;

@Data
@TableName("tb_menu")
public class SystemMenu extends BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

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
