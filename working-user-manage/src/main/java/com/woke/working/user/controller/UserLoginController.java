package com.woke.working.user.controller;

import com.woke.working.common.annotation.VerificationCode;
import com.woke.working.common.dto.user.UserLoginDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.api.user.UserLoginApi;
import com.woke.working.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UserLoginController implements UserLoginApi {

    @Autowired
    UserLoginService userLoginService;

    @Override
    @VerificationCode
    public ResponseVo userLogin(@RequestBody @Valid UserLoginDTO userLoginDTO, HttpServletResponse response) {
        String token = userLoginService.userLogin(userLoginDTO);
        return ResponseVo.success(token);
    }

    @Override
    public ResponseVo userLogout(HttpServletResponse response) {
        userLoginService.userLogout();
        return ResponseVo.success();
    }
}
