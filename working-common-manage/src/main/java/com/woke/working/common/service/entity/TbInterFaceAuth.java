package com.woke.working.common.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.woke.working.db.config.entity.BaseEntity;
import lombok.Data;

/**
 * 白名单管理
 *
 */
@Data
@TableName("tb_interface_auth")
public class TbInterFaceAuth extends BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("access_key")
    private String accessKey;

    @TableField("encryption_key")
    private String encryptionKey;

    @TableField("enable")
    private Boolean enable;
}
