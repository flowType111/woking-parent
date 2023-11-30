package com.woke.working.api.user;

import com.example.working.common.dto.UserLoginDTO;
import com.example.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user-service/login")
public interface UserLoginApi {

    @PostMapping("/userLogin")
    ResponseVo userLogin(UserLoginDTO userLoginDTO);
}
