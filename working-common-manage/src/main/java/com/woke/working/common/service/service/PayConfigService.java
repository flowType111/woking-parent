package com.woke.working.common.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woke.working.common.dto.user.PayConfigDTO;
import com.woke.working.common.dto.user.PayConfigPageDTO;
import com.woke.working.common.service.entity.PayConfig;
import com.woke.working.common.vo.ResponseVo;

public interface PayConfigService extends IService<PayConfig> {

    ResponseVo addPayConfig(PayConfigDTO payConfigDTO);

    ResponseVo deletePayConfig(String id);

    ResponseVo updatePayConfig(PayConfigDTO payConfigDTO);

    ResponseVo selectPayConfig(PayConfigPageDTO payConfigPageDTO);

    ResponseVo selectAll();
}