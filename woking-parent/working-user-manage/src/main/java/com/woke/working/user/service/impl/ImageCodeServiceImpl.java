package com.woke.working.user.service.impl;

import com.wf.captcha.ArithmeticCaptcha;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.constant.RedisKeyConstant;
import com.woke.working.common.dto.user.CheckImageDTO;
import com.woke.working.common.vo.ImageCodeVo;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.redis.util.RedisUtil;
import com.woke.working.user.service.ImageCodeService;
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
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = "";
        try {
            result = new Double(Double.parseDouble(captcha.text())).intValue() + "";
        } catch (Exception e) {
            result = captcha.text();
        }
        //生成uuid做为验证码的key
        UUID key = UUID.randomUUID();
        redisUtil.setString(RedisKeyConstant.VERIFY_CODE_PREFIX + key, result, 5 * 60L);
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
