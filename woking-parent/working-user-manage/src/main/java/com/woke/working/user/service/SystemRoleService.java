package com.woke.working.user.service;

import com.woke.working.common.dto.SystemRoleDTO;
import com.woke.working.common.vo.ResponseVo;

public interface SystemRoleService {

    ResponseVo addRole(SystemRoleDTO systemRoleDTO);

    ResponseVo deleteRole(String id);

    ResponseVo updadteRole(SystemRoleDTO systemRoleDTO);
}
