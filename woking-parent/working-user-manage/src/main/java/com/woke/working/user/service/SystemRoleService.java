package com.woke.working.user.service;

import com.woke.working.common.dto.user.SystemRoleDTO;
import com.woke.working.common.dto.user.SystemRolePageDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface SystemRoleService {

    ResponseVo addRole(SystemRoleDTO systemRoleDTO);

    ResponseVo deleteRole(String id);

    ResponseVo updadteRole(SystemRoleDTO systemRoleDTO);

    ResponseVo selectRolePage(SystemRolePageDTO systemRolePageDTO);

    ResponseVo selectRoleDetails(String id);
}
