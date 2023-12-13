package com.woke.working.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.PageBean;
import com.woke.working.common.dao.InterFaceAuthConfigDao;
import com.woke.working.common.dao.InterFaceAuthDao;
import com.woke.working.common.dto.common.InterFaceAuthDTO;
import com.woke.working.common.dto.common.InterFaceAuthPage;
import com.woke.working.common.entity.TbInterFaceAuth;
import com.woke.working.common.entity.TbInterFaceConfig;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.service.InterFaceAuthConfigService;
import com.woke.working.common.service.InterFaceAuthService;
import com.woke.working.common.util.RSAEncrypt;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class InterFaceAuthServiceImpl extends ServiceImpl<InterFaceAuthDao, TbInterFaceAuth> implements InterFaceAuthService {

    @Autowired
    private RSAEncrypt rsaEncrypt;

    @Autowired
    private InterFaceAuthDao interFaceAuthDao;

    @Autowired
    private InterFaceAuthConfigDao interFaceAuthConfigDao;

    @Autowired
    private InterFaceAuthConfigService interFaceAuthConfigService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo addInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO) {
        TbInterFaceAuth tbInterFaceAuth = this.getOne(new LambdaQueryWrapper<TbInterFaceAuth>()
                .eq(TbInterFaceAuth::getAccessKey, interFaceAuthDTO.getAccessKey())
                .eq(TbInterFaceAuth::getStatus, StatusEnum.ENABLE.getCode()));
        if (Objects.nonNull(tbInterFaceAuth)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_INTERFACE_AUTH_ACCESS_KEY_EXIST);
        }
        tbInterFaceAuth = new TbInterFaceAuth();
        BeanUtils.copyProperties(interFaceAuthDTO, tbInterFaceAuth);
        try {
            tbInterFaceAuth.setEncryptionKey(rsaEncrypt.encryptDefault(interFaceAuthDTO.getEncryptionKey()));
        } catch (Exception e) {
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        this.save(tbInterFaceAuth);
        List<TbInterFaceConfig> tbInterFaceConfigList = new ArrayList<>();
        TbInterFaceAuth finalTbInterFaceAuth = tbInterFaceAuth;
        Optional.ofNullable(interFaceAuthDTO.getOpenApiList()).orElse(new ArrayList<>()).stream().forEach(openList->{
            TbInterFaceConfig tbInterFaceConfig = new TbInterFaceConfig();
            tbInterFaceConfig.setInterfaceAuthId(finalTbInterFaceAuth.getId());
            tbInterFaceConfig.setOpenInterFaceId(openList);
            tbInterFaceConfigList.add(tbInterFaceConfig);
        });
        interFaceAuthConfigService.saveBatch(tbInterFaceConfigList);
        return ResponseVo.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo operateInterFaceAuth(String id, String type) {
        TbInterFaceAuth tbInterFaceAuth = this.getById(id);
        if (Objects.isNull(tbInterFaceAuth)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_INTERFACE_AUTH_ACCESS_KEY_NOT_EXIST);
        }
        if ("delete".equalsIgnoreCase(type)) {
            this.removeById(tbInterFaceAuth);
            interFaceAuthConfigService.remove(new LambdaQueryWrapper<TbInterFaceConfig>()
                    .eq(TbInterFaceConfig::getInterfaceAuthId,tbInterFaceAuth.getId()));
        } else {
            tbInterFaceAuth.setEnable(!tbInterFaceAuth.getEnable());
            this.updateById(tbInterFaceAuth);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo selectInterFaceAuth(InterFaceAuthPage interFaceAuthPage) {
        int total = interFaceAuthDao.selectInterFaceAuthCount(interFaceAuthPage);
        List<TbInterFaceAuth> tbInterFaceAuthList = null;
        if (!CollectionUtils.isEmpty(tbInterFaceAuthList)) {
            tbInterFaceAuthList = interFaceAuthDao.selectInterFaceAuth(interFaceAuthPage);
        }
        PageBean pageBean = new PageBean(interFaceAuthPage.getPageNum(), interFaceAuthPage.getPageSize(), total, tbInterFaceAuthList);
        return ResponseVo.success(pageBean);
    }
}
