package com.woke.working.pubilc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.woke.working.db.config.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("tb_open_interface")
public class TbOpenInterFace extends BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("interface_code")
    private String interFaceCode;

    @TableField("interface_name")
    private String interFaceName;

    @TableField("interface_describe")
    private String interFaceDescribe;
}
