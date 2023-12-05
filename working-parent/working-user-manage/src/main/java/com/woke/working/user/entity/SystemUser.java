package com.woke.working.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.woke.working.web.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("tb_user")
public class SystemUser extends BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("account_no")
    private String accountNo;

    @TableField("password")
    private String password;

    @TableField("nick_name")
    private String nickName;
}
