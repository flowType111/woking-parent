package com.woke.working.user.controller;

import com.woke.working.api.user.SystemRoleApi;
import com.woke.working.common.annotation.AuthorityLimit;
import com.woke.working.common.constant.AuthorityCodeConstant;
import com.woke.working.common.dto.user.SystemRoleDTO;
import com.woke.working.common.dto.user.SystemRolePageDTO;
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
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleManage.Button.add)
    public ResponseVo addRole(@RequestBody @Validated({ValidGroup.Insert.class}) SystemRoleDTO systemRoleDTO) {
        return systemRoleService.addRole(systemRoleDTO);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleManage.Button.delete)
    public ResponseVo deleteRole(@RequestParam("id") @Valid @NotNull(message = "请选择要删除的角色") String id) {
        return systemRoleService.deleteRole(id);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleManage.Button.update)
    public ResponseVo updadteRole(@RequestBody @Validated({ValidGroup.Update.class}) SystemRoleDTO systemRoleDTO) {
        return systemRoleService.updadteRole(systemRoleDTO);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleManage.Button.select)
    public ResponseVo selectRolePage(@RequestBody SystemRolePageDTO systemRolePageDTO) {
        return systemRoleService.selectRolePage(systemRolePageDTO);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleManage.Button.details)
    public ResponseVo selectRoleDetails(@RequestParam("id") String id) {
        return systemRoleService.selectRoleDetails(id);
    }

    @Override
    public ResponseVo selectRoleAll() {
        return systemRoleService.selectRoleAll();
    }
}
