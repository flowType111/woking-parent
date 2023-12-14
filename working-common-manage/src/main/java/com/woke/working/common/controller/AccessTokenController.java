package com.woke.working.common.controller;

import com.woke.working.api.common.AccessTokenApi;
import com.woke.working.common.dto.common.AccessTokenDTO;
import com.woke.working.common.service.AccessTokenService;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessTokenController implements AccessTokenApi {

    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    public ResponseVo getAccessToken(@RequestBody AccessTokenDTO accessTokenDTO) {
        return accessTokenService.getAccessToken(accessTokenDTO);
    }
}
