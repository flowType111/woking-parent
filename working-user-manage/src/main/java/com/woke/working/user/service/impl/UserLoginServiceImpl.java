package com.woke.working.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.bo.UserInfoBO;
import com.woke.working.common.constant.user.UserConstant;
import com.woke.working.common.dto.user.UserLoginDTO;
import com.woke.working.common.util.RSAEncrypt;
import com.woke.working.common.util.user.UserInfoContext;
import com.woke.working.redis.util.RedisUtil;
import com.woke.working.user.dao.SystemMenuDao;
import com.woke.working.user.dao.SystemUserDao;
import com.woke.working.user.entity.SystemMenu;
import com.woke.working.user.entity.SystemUser;
import com.woke.working.user.service.UserLoginService;
import com.woke.working.user.util.TokenUtil;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    @Value("${loginTime}")
    private Long loginTime;

    @Autowired
    private SystemUserDao systemUserDao;

    @Autowired
    private SystemMenuDao systemMenuDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RSAEncrypt rsaEncrypt;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public String userLogin(UserLoginDTO userLoginDTO) {
        SystemUser systemUser = systemUserDao.selectOne(new LambdaQueryWrapper<SystemUser>()
                .eq(SystemUser::getAccountNo, userLoginDTO.getAccountNo()));
        if (Objects.isNull(systemUser)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ACCOUNT_NO_NOT_EXIST);
        }
        String userPassword;
        String inputPassword;
        try {
            userPassword = rsaEncrypt.decryptDefault(systemUser.getPassword());
            inputPassword = rsaEncrypt.decryptDefault(userLoginDTO.getPassword());
        } catch (Exception e) {
            log.error("解析密码错误：{}", e);
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ANALYSIS_PASSWORD_ERROR);
        }
        if (!userPassword.equalsIgnoreCase(inputPassword)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PASSWORD_ERROR);
        }
        String loginTimeMillis = String.valueOf(System.currentTimeMillis());
        String token;
        try {
            token = tokenUtil.getToken(userLoginDTO.getAccountNo(), loginTimeMillis);
        } catch (Exception e) {
            log.error("系统生成用户token失败：{}", e);
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setAccountNo(systemUser.getAccountNo());
        userInfoBO.setNickName(systemUser.getNickName());
        userInfoBO.setLoginTimeMillis(loginTimeMillis);
        userInfoBO.setToken(token);
        List<String> systemMenuList = systemMenuDao.selectUserRoleMeun(systemUser.getAccountNo());
        userInfoBO.setJurisdictionCodeList(systemMenuList);
        redisUtil.setObject(UserConstant.USER_ACCOUNT_PREFIX + userLoginDTO.getAccountNo(), userInfoBO, loginTime);
        return token;
    }

    @Override
    public void userLogout() {
        UserInfoBO userInfoBO = UserInfoContext.getLoginUser();
        if (userInfoBO == null) {
            throw new BusinessErrorException(BusinessMsgEnum.NO_AUTH);
        }
        redisUtil.delete(UserConstant.USER_ACCOUNT_PREFIX + userInfoBO.getAccountNo());
    }
}
