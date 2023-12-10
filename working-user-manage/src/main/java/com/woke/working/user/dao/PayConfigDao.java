package com.woke.working.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.common.dto.user.PayConfigPageDTO;
import com.woke.working.user.entity.PayConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayConfigDao extends BaseMapper<PayConfig> {

    int selectPayConfig(@Param("payConfigPageDTO") PayConfigPageDTO payConfigPageDTO);

    List<PayConfig> selectPayConfigPage(@Param("payConfigPageDTO") PayConfigPageDTO payConfigPageDTO);
}
