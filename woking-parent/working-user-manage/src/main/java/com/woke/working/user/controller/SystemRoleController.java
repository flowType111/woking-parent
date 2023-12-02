package com.woke.working.user.controller;

import com.woke.working.api.user.SystemRoleApi;
import com.woke.working.common.dto.SystemRoleDTO;
import com.woke.working.common.valid.ValidGroup;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class SystemRoleController implements SystemRoleApi {

    @Autowired
    private SystemRoleService systemRoleService;
    @Override
    public ResponseVo addRole(@RequestBody @Validated({ValidGroup.Insert.class}) SystemRoleDTO systemRoleDTO) {
        return systemRoleService.addRole(systemRoleDTO);
    }

    @Override
    public ResponseVo deleteRole(@RequestParam("id") @Valid @NotNull(message = "请选择要删除的角色") String id) {
        return systemRoleService.deleteRole(id);
    }

    @Override
    public ResponseVo updadteRole(@RequestBody @Validated({ValidGroup.Update.class}) SystemRoleDTO systemRoleDTO) {
        return systemRoleService.updadteRole(systemRoleDTO);
    }
}
