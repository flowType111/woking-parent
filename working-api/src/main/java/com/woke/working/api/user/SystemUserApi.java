package com.woke.working.api.user;

import com.woke.working.common.dto.user.SystemUserDTO;
import com.woke.working.common.dto.user.SystemUserPageDTO;
import com.woke.working.common.vo.ResponseVo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user-service/account")
public interface SystemUserApi {

    @PostMapping("/addUser")
    ResponseVo add(SystemUserDTO userDTO);

    @DeleteMapping("/deleteUser")
    ResponseVo deleteUser(String id);

    @PostMapping("/updateUser")
    ResponseVo updateUser(SystemUserDTO userDTO);

    @PostMapping("/selectUserPage")
    ResponseVo selectUserPage(SystemUserPageDTO systemUserPageDTO);

    @GetMapping("/selectUserDetails")
    ResponseVo selectUserDetails(String id);
    
    @GetMapping("/check")
    ResponseVo check(String id, String name);
    
    @GetMapping("/queryUserRole")
    ResponseVo queryUserRole(String userid);
}
