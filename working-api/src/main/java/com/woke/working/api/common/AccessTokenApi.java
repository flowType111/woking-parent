package com.woke.working.api.common;

import com.woke.working.common.dto.common.AccessTokenDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/working/access")
public interface AccessTokenApi {

    @RequestMapping("/getAccessToken")
    ResponseVo getAccessToken(AccessTokenDTO accessTokenDTO);
}
