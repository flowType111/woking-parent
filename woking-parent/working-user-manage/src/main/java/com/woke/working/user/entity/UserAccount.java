package com.woke.working.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_user")
public class UserAccount {

    @TableField("account_no")
    private String accountNo;

    @TableField("password")
    private String password;

    @TableField("nick_name")
    private String nickName;
}
