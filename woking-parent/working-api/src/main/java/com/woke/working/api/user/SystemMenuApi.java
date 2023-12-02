package com.woke.working.api.user;

import com.woke.working.common.dto.SystemMenuDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/system/menu")
public interface SystemMenuApi {

    @PostMapping("/addMenu")
    ResponseVo addMenu(SystemMenuDTO systemMenuDTO);

    @GetMapping("/deleteMenu")
    ResponseVo deleteMenu(String id);

    @PostMapping("/updateMenu")
    ResponseVo updateMenu(SystemMenuDTO systemMenuDTO);
}
