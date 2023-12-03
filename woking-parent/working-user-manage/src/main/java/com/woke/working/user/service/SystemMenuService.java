package com.woke.working.user.service;

import com.woke.working.common.dto.user.SystemMenuDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.vo.ResponseVo;

public interface SystemMenuService {

    ResponseVo addMenu(SystemMenuDTO systemMenuDTO);

    ResponseVo deleteMenu(String id);

    ResponseVo updateMenu(SystemMenuDTO systemMenuDTO);

    ResponseVo selectMenuPage(SystemMenuPageDTO systemMenuPageDTO);

    ResponseVo selectMenu();
}
