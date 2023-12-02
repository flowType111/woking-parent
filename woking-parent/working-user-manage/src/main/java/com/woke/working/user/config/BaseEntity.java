package com.woke.working.user.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库模型设计时抽出所有通用字段，抽象为父类
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最后更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time" ,fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 是否删除
     * 框架提供的逻辑删除注解。如果使用delete关键字，会报错
     * 如果进行查询，会过滤被逻辑删除的字段！
     *  0 未删除
     *  1 已删除
     */
    @TableLogic
    private Boolean status;
}
