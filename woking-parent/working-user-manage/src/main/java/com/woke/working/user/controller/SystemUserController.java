package com.woke.working.user.controller;

import com.woke.working.common.annotation.AuthorityLimit;
import com.woke.working.common.constant.AuthorityCodeConstant;
import com.woke.working.common.dto.user.SystemUserDTO;
import com.woke.working.common.dto.user.SystemUserPageDTO;
import com.woke.working.common.valid.ValidGroup;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.api.user.SystemUserApi;
import com.woke.working.user.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemUserController implements SystemUserApi {

    @Autowired
    private SystemUserService userService;

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserManage.Button.add)
    public ResponseVo add(@RequestBody @Validated({ValidGroup.Insert.class}) SystemUserDTO systemUserDTO) {
        return userService.add(systemUserDTO);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserManage.Button.delete)
    public ResponseVo deleteUser(@RequestParam("id") String id) {
        return userService.deleteUser(id);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserManage.Button.update)
    public ResponseVo updateUser(@RequestBody @Validated({ValidGroup.Update.class}) SystemUserDTO systemUserDTO) {
        return userService.updateUser(systemUserDTO);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserManage.Button.select)
    public ResponseVo selectUserPage(@RequestBody SystemUserPageDTO systemUserPageDTO) {
        return userService.selectUserPage(systemUserPageDTO);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserManage.Button.details)
    public ResponseVo selectUserDetails(@RequestParam("id")String id) {
        return userService.selectUserDetails(id);
    }

}
