package com.woke.working.common.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woke.working.common.service.dao.InterFaceAuthConfigDao;
import com.woke.working.common.service.entity.TbInterFaceConfig;
import com.woke.working.common.service.service.InterFaceAuthConfigService;
import org.springframework.stereotype.Service;

@Service
public class InterFaceAuthConfigServiceImpl extends ServiceImpl<InterFaceAuthConfigDao, TbInterFaceConfig> implements InterFaceAuthConfigService {
}
