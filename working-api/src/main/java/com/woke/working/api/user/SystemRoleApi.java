package com.woke.working.api.user;

import com.alibaba.fastjson.JSONObject;
import com.woke.working.common.dto.user.SystemRoleDTO;
import com.woke.working.common.dto.user.SystemRolePageDTO;
import com.woke.working.common.vo.ResponseVo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/system/role")
public interface SystemRoleApi {

    @PostMapping("/addRole")
    ResponseVo addRole(SystemRoleDTO systemRoleDTO);

    @DeleteMapping("/deleteRole")
    ResponseVo deleteRole(String id);

    @PostMapping("/updateRole")
    ResponseVo updadteRole(SystemRoleDTO systemRoleDTO);

    @PostMapping("/selectRolePage")
    ResponseVo selectRolePage(SystemRolePageDTO systemRolePageDTO);
    

    @GetMapping("/selectRoleDetails")
    ResponseVo selectRoleDetails(String id);

    @GetMapping("/selectRoleAll")
    ResponseVo selectRoleAll();
    
    @GetMapping("/checkRoleCode")
    ResponseVo checkRoleCode(String id, String roleCode);
    
    @PostMapping("/saveRolePermission")
    ResponseVo saveRolePermission(@RequestBody JSONObject json);
}
