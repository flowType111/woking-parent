package com.woke.working.user.controller;

import com.woke.working.common.dto.SystemMenuDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.api.user.SystemMenuApi;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SystemMenuController implements SystemMenuApi {

    @Override
    public ResponseVo addMenu(@RequestBody @Valid SystemMenuDTO systemMenuDTO) {
        return null;
    }
}
