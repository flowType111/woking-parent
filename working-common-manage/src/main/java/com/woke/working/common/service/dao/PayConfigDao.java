package com.woke.working.common.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.dto.user.PayConfigPageDTO;
import com.woke.working.common.service.entity.PayConfig;
import org.apache.ibatis.annotations.Param;


public interface PayConfigDao extends BaseMapper<PayConfig> {

    Page<PayConfig> selectPayConfigPage(@Param("page") Page<PayConfig> page, @Param("payConfigPageDTO") PayConfigPageDTO payConfigPageDTO);
}
