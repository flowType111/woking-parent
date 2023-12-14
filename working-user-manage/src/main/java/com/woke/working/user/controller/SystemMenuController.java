package com.woke.working.user.controller;

import com.woke.working.common.annotation.AuthorityLimit;
import com.woke.working.common.constant.AuthorityCodeConstant;
import com.woke.working.common.dto.user.SysPermissionDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.api.user.SystemMenuApi;
import com.woke.working.user.entity.SysPermission;
import com.woke.working.user.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class SystemMenuController implements SystemMenuApi {

    @Autowired
    private SystemMenuService systemMenuService;

    @Override
   // @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleMenuManage.Button.add)
    public ResponseVo addMenu(SysPermissionDTO permission) {
        return systemMenuService.addPermission(permission);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleMenuManage.Button.delete)
    public ResponseVo deleteMenu(@RequestParam("id") @Valid @NotNull(message = "请选择要删除的权限点") String id) {
        return systemMenuService.deleteMenu(id);
    }

    @Override
    //@AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleMenuManage.Button.update)
    public ResponseVo updateMenu(SysPermissionDTO permission) {
        return systemMenuService.updatePermission(permission);
    }

    @Override
   // @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleMenuManage.Button.select)
    public ResponseVo selectMenuPage(@RequestBody SystemMenuPageDTO systemMenuPageDTO) {
        return systemMenuService.selectMenuPage(systemMenuPageDTO);
    }

    @Override
    public ResponseVo selectMenu() {
        return systemMenuService.selectMenu();
    }

	@Override
	public ResponseVo getPermCode() {
		return systemMenuService.getPermCode();
	}

	@Override
	public ResponseVo getUserPermissionByToken(HttpServletRequest request) {
		return systemMenuService.getUserPermissionByToken(request);
	}

	@Override
	public ResponseVo queryTreeList() {
		return systemMenuService.queryTreeList();
	}

	@Override
	public ResponseVo queryRolePermission(String roleId) {
		return systemMenuService.queryRolePermission(roleId);
	}

	@Override
	public ResponseVo checkPermDuplication(String id, String url, Boolean alwaysShow) {
		return systemMenuService.checkPermDuplication(id, url, alwaysShow);
	}
}
