package com.woke.working.common.service.impl;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.constant.RedisKeyConstant;
import com.woke.working.common.dto.user.CheckImageDTO;
import com.woke.working.common.service.ImageCodeService;
import com.woke.working.common.vo.ImageCodeVo;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.redis.util.RedisUtil;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ImageCodeServiceImpl implements ImageCodeService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResponseVo<ImageCodeVo> getImageCode() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);//字母数字混合
        // 获取运算的结果
        String result = "";
        try {
            result = specCaptcha.toBase64();
        } catch (Exception e) {
            throw new BusinessErrorException(BusinessMsgEnum.AUTH_TOKEN_ERROR);
        }
        //生成uuid做为验证码的key
        UUID key = UUID.randomUUID();
        redisUtil.setString(RedisKeyConstant.VERIFY_CODE_PREFIX + key, specCaptcha.text(), 5 * 60L);
        return ResponseVo.success(new ImageCodeVo(key.toString() ,result));
    }

    @Override
    public Boolean verifyCode(CheckImageDTO checkImageDTO) {
        String checkCode = redisUtil.getString(RedisKeyConstant.VERIFY_CODE_PREFIX + checkImageDTO.getRandomCode());
        if (StringUtils.isEmpty(checkCode)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_CHECK_CODE_INVALID);
        }
        if (!checkCode.equalsIgnoreCase(checkImageDTO.getImageCode())){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_CHECK_CODE_ERROR);
        }
        redisUtil.delete(RedisKeyConstant.VERIFY_CODE_PREFIX + checkImageDTO.getRandomCode());
        return true;
    }
}
