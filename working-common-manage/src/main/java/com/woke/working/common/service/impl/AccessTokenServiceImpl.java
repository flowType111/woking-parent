package com.woke.working.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.constant.RedisKeyConstant;
import com.woke.working.common.constant.common.BaseSlaConstant;
import com.woke.working.common.constant.user.UserConstant;
import com.woke.working.common.dao.InterFaceAuthDao;
import com.woke.working.common.dto.common.AccessTokenDTO;
import com.woke.working.common.entity.TbInterFaceAuth;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.service.AccessTokenService;
import com.woke.working.common.util.Base64Util;
import com.woke.working.common.util.RSAEncrypt;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.common.vo.common.AccessTokenVo;
import com.woke.working.redis.util.RedisUtil;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private InterFaceAuthDao interFaceAuthDao;

    @Autowired
    private RSAEncrypt rsaEncrypt;

    @Autowired
    private RedisUtil redisUtil;


    @Value("${expiresTime}")
    private Long expiresTime;

    @Override
    public ResponseVo getAccessToken(AccessTokenDTO accessTokenDTO) {
        // 查询当前账号白名单
        TbInterFaceAuth tbInterFaceAuth = interFaceAuthDao.selectOne(new LambdaQueryWrapper<TbInterFaceAuth>()
                .eq(TbInterFaceAuth::getAccessKey,accessTokenDTO.getAccessKey())
                .eq(TbInterFaceAuth::getStatus, StatusEnum.ENABLE.getCode()));
        if (Objects.isNull(tbInterFaceAuth)){
            throw new BusinessErrorException(BusinessMsgEnum.INTERFACE_AUTH_ACCOUNT_NOT_EXIST);
        }
        // 如果白名单账号被禁用
        if (!tbInterFaceAuth.getEnable()){
            throw new BusinessErrorException(BusinessMsgEnum.INTERFACE_AUTH_ACCOUNT_DISABLE);
        }
        String encryptionKey = Base64Util.decode(accessTokenDTO.getEncryptionKey().getBytes());
        String dbEncryptionKey = Base64Util.decode(tbInterFaceAuth.getEncryptionKey().getBytes());
        if (!encryptionKey.equalsIgnoreCase(dbEncryptionKey)) {
            throw new BusinessErrorException(BusinessMsgEnum.INTERFACE_AUTH_PASSWORD_ERROR);
        }
        StringBuffer stringBuffer= new StringBuffer();
        stringBuffer.append(accessTokenDTO.getAccessKey()).append("#").append(accessTokenDTO.getEncryptionKey()).append(UUID.randomUUID());
        String token = Base64Util.encode(stringBuffer.toString().getBytes());
        redisUtil.setObject(RedisKeyConstant.SIGN_CODE_KEY + accessTokenDTO.getAccessKey(), token, expiresTime);
        AccessTokenVo accessTokenVo = new AccessTokenVo();
        accessTokenVo.setAccessKey(tbInterFaceAuth.getAccessKey());
        accessTokenVo.setAccessToken(token);
        accessTokenVo.setExpiresTime(expiresTime);
        return ResponseVo.success(accessTokenVo);
    }
}
