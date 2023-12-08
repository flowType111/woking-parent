package com.woke.working.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.woke.working.common.enumeration.order.PayStatusEnum;
import com.woke.working.common.enumeration.web.PayChannelEnum;
import com.woke.working.web.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("tb_order")
public class PayOrder extends BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("order_no")
    private String orderNo;

    @TableField("order_amount")
    private BigDecimal orderAmount;

    @TableField("order_status")
    private PayStatusEnum orderStatus;

    @TableField("order_pay_channel")
    private PayChannelEnum payChannelEnum;
}
