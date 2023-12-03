package com.woke.working.api.user;

import com.woke.working.common.dto.user.UserLoginDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user-service/login")
public interface UserLoginApi {

    @PostMapping("/userLogin")
    ResponseVo userLogin(UserLoginDTO userLoginDTO);
}
