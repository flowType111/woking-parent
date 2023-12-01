package com.woke.working.api.user;

import com.woke.working.common.dto.SystemMenuDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/system/menu")
public interface SystemMenuApi {

    @PostMapping("/addMenu")
    ResponseVo addMenu(SystemMenuDTO systemMenuDTO);
}
