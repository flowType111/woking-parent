package com.woke.working.user.controller;

import com.woke.working.api.user.UserAuthApi;
import com.woke.working.common.dto.user.UserTokenDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserAuthController implements UserAuthApi {

    @Autowired
    private UserAuthService userAuthService;

    @Override
    public ResponseVo getAuthInfo(@RequestBody UserTokenDTO userTokenDTO) {
        return userAuthService.getAuthInfo(userTokenDTO);
    }
}
