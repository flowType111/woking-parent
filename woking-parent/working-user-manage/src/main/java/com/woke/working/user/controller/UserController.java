package com.woke.working.user.controller;

import com.woke.working.common.dto.UserDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.api.user.UserApi;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseVo add(@RequestBody @Valid UserDTO userDTO) {
        return null;
    }
}
