package com.woke.working.common.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.PageBean;
import com.woke.working.common.constant.RedisKeyConstant;
import com.woke.working.common.constant.common.BaseSlaConstant;
import com.woke.working.common.dto.common.AccessTokenAuthDTO;
import com.woke.working.common.service.entity.TbInterFaceConfig;
import com.woke.working.common.service.service.InterFaceAuthService;
import com.woke.working.common.service.dao.InterFaceAuthDao;
import com.woke.working.common.dto.common.InterFaceAuthDTO;
import com.woke.working.common.dto.common.InterFaceAuthPage;
import com.woke.working.common.service.entity.TbInterFaceAuth;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.service.service.InterFaceAuthConfigService;
import com.woke.working.common.service.util.Base64Util;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.common.vo.common.InterFaceConfigVo;
import com.woke.working.common.vo.common.InterFaceVo;
import com.woke.working.redis.util.RedisUtil;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InterFaceAuthServiceImpl extends ServiceImpl<InterFaceAuthDao, TbInterFaceAuth> implements InterFaceAuthService {
    @Autowired
    private InterFaceAuthDao interFaceAuthDao;
    @Autowired
    private InterFaceAuthConfigService interFaceAuthConfigService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo addInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO) {
        TbInterFaceAuth tbInterFaceAuth = this.getOne(new LambdaQueryWrapper<TbInterFaceAuth>().eq(TbInterFaceAuth::getAccessKey, interFaceAuthDTO.getAccessKey()).eq(TbInterFaceAuth::getStatus, StatusEnum.ENABLE.getCode()));
        if (Objects.nonNull(tbInterFaceAuth)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_INTERFACE_AUTH_ACCESS_KEY_EXIST);
        }
        tbInterFaceAuth = new TbInterFaceAuth();
        BeanUtils.copyProperties(interFaceAuthDTO, tbInterFaceAuth);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(BaseSlaConstant.sla).append(interFaceAuthDTO.getEncryptionKey());
            tbInterFaceAuth.setEncryptionKey(Base64Util.encode(stringBuffer.toString().getBytes()));
        } catch (Exception e) {
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        this.save(tbInterFaceAuth);
        List<TbInterFaceConfig> tbInterFaceConfigList = new ArrayList<>();
        TbInterFaceAuth finalTbInterFaceAuth = tbInterFaceAuth;
        Optional.ofNullable(interFaceAuthDTO.getOpenApiList()).orElse(new ArrayList<>()).stream().forEach(openList -> {
            TbInterFaceConfig tbInterFaceConfig = new TbInterFaceConfig();
            tbInterFaceConfig.setInterfaceAuthId(finalTbInterFaceAuth.getId());
            tbInterFaceConfig.setOpenInterFaceId(openList.getInterfaceCode());
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
            interFaceAuthConfigService.remove(new LambdaQueryWrapper<TbInterFaceConfig>().eq(TbInterFaceConfig::getInterfaceAuthId, tbInterFaceAuth.getId()));
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

    @Override
    public ResponseVo updateInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO) {
        if (!CollectionUtils.isEmpty(interFaceAuthDTO.getOpenApiList())) {
            interFaceAuthConfigService.remove(new LambdaQueryWrapper<TbInterFaceConfig>().eq(TbInterFaceConfig::getInterfaceAuthId, interFaceAuthDTO.getId()));
            List<TbInterFaceConfig> tbInterFaceConfigList = new ArrayList<>();
            Optional.ofNullable(interFaceAuthDTO.getOpenApiList()).orElse(new ArrayList<>()).stream().forEach(openList -> {
                TbInterFaceConfig tbInterFaceConfig = new TbInterFaceConfig();
                tbInterFaceConfig.setInterfaceAuthId(interFaceAuthDTO.getId());
                tbInterFaceConfig.setOpenInterFaceId(openList.getInterfaceCode());
                tbInterFaceConfigList.add(tbInterFaceConfig);
            });
            interFaceAuthConfigService.saveBatch(tbInterFaceConfigList);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo selectInterFaceDetails(String id) {
        return ResponseVo.success(interFaceAuthDao.selectInterFaceDetails(id));
    }

    @Override
    public ResponseVo interFaceAuth(AccessTokenAuthDTO accessTokenAuthDTO) {
        TbInterFaceAuth tbInterFaceAuth = interFaceAuthDao.selectOne(new LambdaQueryWrapper<TbInterFaceAuth>()
                .eq(TbInterFaceAuth::getAccessKey,accessTokenAuthDTO.getAccessKey()));
        if (Objects.isNull(tbInterFaceAuth)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_INTERFACE_AUTH_ACCESS_KEY_NOT_EXIST);
        }
        if (!tbInterFaceAuth.getEnable()){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_OPEN_INTERFACE_ACCESS_ENABLE);
        }
        String token = redisUtil.getString(RedisKeyConstant.SIGN_CODE_KEY + accessTokenAuthDTO.getAccessKey());
        if (StringUtils.isEmpty(token)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_TOKEN_EXPIRE);
        }
        if (!accessTokenAuthDTO.getAccessToken().equalsIgnoreCase(token)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_TOKEN_INCORRECT);
        }
        InterFaceVo interFaceVo = interFaceAuthDao.selectInterFaceDetails(tbInterFaceAuth.getId());
        List<String> code = Optional.ofNullable(interFaceVo.getInterFaceConfigVos()).orElse(new ArrayList<>())
                .stream().map(InterFaceConfigVo::getInterFaceCode).collect(Collectors.toList());
        return ResponseVo.success(code);
    }
}
