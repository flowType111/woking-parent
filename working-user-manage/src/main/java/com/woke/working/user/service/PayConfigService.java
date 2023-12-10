package com.woke.working.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woke.working.common.dto.user.PayConfigDTO;
import com.woke.working.common.dto.user.PayConfigPageDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.entity.PayConfig;

public interface PayConfigService extends IService<PayConfig> {

    ResponseVo addPayConfig(PayConfigDTO payConfigDTO);

    ResponseVo deletePayConfig(String id);

    ResponseVo updatePayConfig(PayConfigDTO payConfigDTO);

    ResponseVo selectPayConfig(PayConfigPageDTO payConfigPageDTO);

    ResponseVo selectAll();
}
