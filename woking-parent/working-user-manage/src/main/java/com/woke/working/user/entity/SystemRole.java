package com.woke.working.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.woke.working.user.config.BaseEntity;
import lombok.Data;

/**
 * 角色表
 *
 */
@Data
@TableName("tb_role")
public class SystemRole extends BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("role_code")
    private String roleCode;

    @TableField("role_name")
    private String roleName;
}
