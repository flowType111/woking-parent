package com.woke.working.user.service;

import com.woke.working.common.dto.SystemMenuDTO;
import com.woke.working.common.valid.ValidGroup;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface SystemMenuService {

    ResponseVo addMenu(SystemMenuDTO systemMenuDTO);

    ResponseVo deleteMenu(String id);

    ResponseVo updateMenu(SystemMenuDTO systemMenuDTO);
}
