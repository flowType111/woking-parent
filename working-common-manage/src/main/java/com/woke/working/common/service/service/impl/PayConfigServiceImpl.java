package com.woke.working.common.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.PageBean;
import com.woke.working.common.dto.user.PayConfigDTO;
import com.woke.working.common.dto.user.PayConfigPageDTO;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.service.dao.PayConfigDao;
import com.woke.working.common.service.entity.PayConfig;
import com.woke.working.common.service.service.PayConfigService;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class PayConfigServiceImpl extends ServiceImpl<PayConfigDao, PayConfig> implements PayConfigService {

    @Autowired
    private PayConfigDao payConfigDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo addPayConfig(PayConfigDTO payConfigDTO) {
        // 判断支付类型如果已配置要提示存在
        PayConfig payConfig = payConfigDao.selectOne(new LambdaQueryWrapper<PayConfig>()
                .eq(PayConfig::getPaymentMethod, payConfigDTO.getPaymentMethod())
                .eq(PayConfig::getStatus,StatusEnum.ENABLE.getCode()));
        if (Objects.nonNull(payConfig)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PAY_CONFIG_EXIST);
        }
        payConfig = new PayConfig();
        BeanUtils.copyProperties(payConfigDTO, payConfig);
        payConfigDao.insert(payConfig);
        return ResponseVo.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo deletePayConfig(String id) {
        PayConfig payConfig = payConfigDao.selectById(id);
        if (Objects.isNull(payConfig)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PAY_CONFIG_NOT_EXIST);
        }
        payConfig.setStatus(false);
        this.removeById(payConfig);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo updatePayConfig(PayConfigDTO payConfigDTO) {
        PayConfig payConfig = payConfigDao.selectById(payConfigDTO.getId());
        if (Objects.isNull(payConfig)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PAY_CONFIG_NOT_EXIST);
        }
        BeanUtils.copyProperties(payConfigDTO, payConfig);
        this.updateById(payConfig);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo selectPayConfig(PayConfigPageDTO payConfigPageDTO) {
        Page<PayConfig> page = new Page<PayConfig>(payConfigPageDTO.getOffSet(), payConfigPageDTO.getPageSize());
        Page<PayConfig> payConfigPage = payConfigDao.selectPayConfigPage(page,payConfigPageDTO);
        return ResponseVo.success(payConfigPage);
    }

    @Override
    public ResponseVo selectAll() {
        List<PayConfig> payConfigList = payConfigDao.selectList(new LambdaQueryWrapper<PayConfig>()
                .eq(PayConfig::getStatus, StatusEnum.ENABLE.getCode()));
        return ResponseVo.success(payConfigList);
    }
}
