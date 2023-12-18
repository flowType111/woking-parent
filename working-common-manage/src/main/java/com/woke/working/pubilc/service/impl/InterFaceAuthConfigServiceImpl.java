package com.woke.working.pubilc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woke.working.pubilc.dao.InterFaceAuthConfigDao;
import com.woke.working.pubilc.entity.TbInterFaceConfig;
import com.woke.working.pubilc.service.InterFaceAuthConfigService;
import org.springframework.stereotype.Service;

@Service
public class InterFaceAuthConfigServiceImpl extends ServiceImpl<InterFaceAuthConfigDao, TbInterFaceConfig> implements InterFaceAuthConfigService {
}
