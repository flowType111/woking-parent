package com.woke.working.api.user;

import com.woke.working.common.dto.user.SystemMenuDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/system/menu")
public interface SystemMenuApi {

    @PostMapping("/addMenu")
    ResponseVo addMenu(SystemMenuDTO systemMenuDTO);

    @GetMapping("/deleteMenu")
    ResponseVo deleteMenu(String id);

    @PostMapping("/updateMenu")
    ResponseVo updateMenu(SystemMenuDTO systemMenuDTO);

    @PostMapping("/selectMenuPage")
    ResponseVo selectMenuPage(SystemMenuPageDTO systemMenuPageDTO);

    @PostMapping("/selectMenu")
    ResponseVo selectMenu();
}
