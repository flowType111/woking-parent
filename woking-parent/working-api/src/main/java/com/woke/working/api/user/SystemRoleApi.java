package com.woke.working.api.user;

import com.woke.working.common.dto.SystemRoleDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/system/role")
public interface SystemRoleApi {

    @PostMapping("/addRole")
    ResponseVo addRole(SystemRoleDTO systemRoleDTO);

    @GetMapping("/deleteRole")
    ResponseVo deleteRole(String id);

    @PostMapping("/updateRole")
    ResponseVo updadteRole(SystemRoleDTO systemRoleDTO);
}
