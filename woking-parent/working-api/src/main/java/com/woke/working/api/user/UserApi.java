package com.woke.working.api.user;

import com.woke.working.common.dto.user.UserDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user-service/account")
public interface UserApi {

    @PostMapping
    ResponseVo add(UserDTO userDTO);
}
