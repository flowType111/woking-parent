package com.woke.working.api.user;

import com.woke.working.common.dto.user.SysPermissionDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.vo.ResponseVo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/system/menu")
public interface SystemMenuApi {

    @PostMapping("/addMenu")
    ResponseVo addMenu(@RequestBody SysPermissionDTO permission);

    @DeleteMapping("/deleteMenu")
    ResponseVo deleteMenu(String id);

    @PostMapping("/updateMenu")
    ResponseVo updateMenu(@RequestBody SysPermissionDTO permission);

    @PostMapping("/selectMenuPage")
    ResponseVo selectMenuPage(SystemMenuPageDTO systemMenuPageDTO);

    @PostMapping("/selectMenu")
    ResponseVo selectMenu();
    
    @GetMapping("/getPermCode")
    ResponseVo getPermCode();
    
    @GetMapping("/getUserPermissionByToken")
    ResponseVo getUserPermissionByToken(HttpServletRequest request);
    
    @GetMapping("/queryTreeList")
    ResponseVo queryTreeList();
    
    @GetMapping("/queryRolePermission")
    ResponseVo queryRolePermission(String roleId);
    
    @GetMapping("/checkPermDuplication")
	ResponseVo checkPermDuplication(@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "url") String url, @RequestParam(name = "alwaysShow") Boolean alwaysShow);
}
