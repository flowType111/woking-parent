package com.woke.working.common.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.woke.working.db.config.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("tb_qr_code")
public class QrCode extends BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("qr_code_id")
    private String qrCodeId;

    @TableField("qr_code_path")
    private String qrCodePath;

    @TableField("callback_url")
    private String callbackUrl;

    @TableField("qr_code_status")
    private Integer qrCodeStatus;
}
