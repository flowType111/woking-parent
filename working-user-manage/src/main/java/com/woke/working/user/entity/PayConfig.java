package com.woke.working.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_pay_config")
public class PayConfig extends BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("payment_method")
    private String paymentMethod;

    @TableField("payment_name")
    private String paymentName;

    @TableField("image_path")
    private String imagePath;
}
