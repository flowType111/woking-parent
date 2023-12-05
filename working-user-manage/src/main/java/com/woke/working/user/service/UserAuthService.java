package com.woke.working.user.service;

import com.woke.working.common.dto.user.UserTokenDTO;
import com.woke.working.common.vo.ResponseVo;

public interface UserAuthService {

    ResponseVo getAuthInfo(UserTokenDTO userTokenDTO);
}
