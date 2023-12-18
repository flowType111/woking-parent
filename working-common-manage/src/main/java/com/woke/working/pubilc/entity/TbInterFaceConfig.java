package com.woke.working.pubilc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_interface_config")
public class TbInterFaceConfig {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("interface_auth_id")
    private String interfaceAuthId;

    @TableField("open_interface_id")
    private String openInterFaceId;
}
