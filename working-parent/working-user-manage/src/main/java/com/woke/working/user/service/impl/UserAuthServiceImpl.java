package com.woke.working.user.service.impl;

import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.bo.TokenInfoBO;
import com.woke.working.common.bo.UserInfoBO;
import com.woke.working.common.constant.user.UserConstant;
import com.woke.working.common.dto.user.UserTokenDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.redis.util.RedisUtil;
import com.woke.working.user.service.UserAuthService;
import com.woke.working.user.util.TokenUtil;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService {


    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResponseVo getAuthInfo(UserTokenDTO userTokenDTO) {
        TokenInfoBO tokenInfoBO = tokenUtil.geTokenInfoBO(userTokenDTO.getToken());
        log.info("tokenInfoBO:" + tokenInfoBO);
        UserInfoBO userInfoBO = redisUtil.getObject(UserConstant.USER_ACCOUNT_PREFIX + tokenInfoBO.getAccountNo(), UserInfoBO.class);
        if (userInfoBO == null) {
            throw new BusinessErrorException(BusinessMsgEnum.NO_AUTH);
        }
        //token 中的登陆时间与redis中的登陆时间不一致
        if (!tokenInfoBO.getLoginTimeMillis().equals(userInfoBO.getLoginTimeMillis())) {
            throw new BusinessErrorException(BusinessMsgEnum.OTHER_LOGIN);
        }
        return ResponseVo.success(userInfoBO);
    }
}
