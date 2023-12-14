package com.woke.working.user.service;

import javax.servlet.http.HttpServletRequest;

import com.woke.working.common.dto.user.SysPermissionDTO;
import com.woke.working.common.dto.user.SystemMenuDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.entity.SysPermission;

public interface SystemMenuService {

    ResponseVo addMenu(SystemMenuDTO systemMenuDTO);

    ResponseVo deleteMenu(String id);

    ResponseVo updateMenu(SystemMenuDTO systemMenuDTO);

    ResponseVo selectMenuPage(SystemMenuPageDTO systemMenuPageDTO);

    ResponseVo selectMenu();

	ResponseVo getPermCode();

	ResponseVo getUserPermissionByToken(HttpServletRequest request);

	ResponseVo queryTreeList();

	ResponseVo queryRolePermission(String roleId);

	ResponseVo checkPermDuplication(String id, String url, Boolean alwaysShow);
	
	ResponseVo addPermission(SysPermissionDTO sysPermission);

	ResponseVo updatePermission(SysPermissionDTO permission);
}
