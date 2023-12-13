package com.woke.working.user.service;

import javax.servlet.http.HttpServletRequest;

import com.woke.working.common.dto.user.SystemMenuDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.vo.ResponseVo;

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
}
