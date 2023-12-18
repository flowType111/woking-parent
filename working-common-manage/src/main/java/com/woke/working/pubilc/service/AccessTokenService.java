package com.woke.working.pubilc.service;

import com.woke.working.common.dto.common.AccessTokenDTO;
import com.woke.working.common.vo.ResponseVo;

public interface AccessTokenService {

    ResponseVo getAccessToken(AccessTokenDTO accessTokenDTO);
}
