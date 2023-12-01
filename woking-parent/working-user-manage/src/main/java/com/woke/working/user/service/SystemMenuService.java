package com.woke.working.user.service;

import com.woke.working.common.dto.SystemMenuDTO;
import com.woke.working.common.vo.ResponseVo;

public interface SystemMenuService {

    ResponseVo addMenu(SystemMenuDTO systemMenuDTO);
}
