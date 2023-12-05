package com.woke.working.api.user;

import com.woke.working.common.dto.user.UserTokenDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user-service/auth")
public interface UserAuthApi {

    @PostMapping("/getAuthInfo")
    ResponseVo getAuthInfo(UserTokenDTO userTokenDTO);
}
