package com.woke.working.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_user_role")
public class SystemUserRole {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("user_id")
    private String userId;
}
