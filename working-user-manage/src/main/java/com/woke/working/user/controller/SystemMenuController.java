package com.woke.working.user.controller;

import com.woke.working.common.annotation.AuthorityLimit;
import com.woke.working.common.constant.AuthorityCodeConstant;
import com.woke.working.common.dto.user.SystemMenuDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.valid.ValidGroup;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.api.user.SystemMenuApi;
import com.woke.working.user.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
public class SystemMenuController implements SystemMenuApi {

    @Autowired
    private SystemMenuService systemMenuService;

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleMenuManage.Button.add)
    public ResponseVo addMenu(@RequestBody @Validated({ValidGroup.Insert.class}) SystemMenuDTO systemMenuDTO) {
        return systemMenuService.addMenu(systemMenuDTO);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleMenuManage.Button.delete)
    public ResponseVo deleteMenu(@RequestParam("id") @Valid @NotNull(message = "请选择要删除的权限点") String id) {
        return systemMenuService.deleteMenu(id);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleMenuManage.Button.update)
    public ResponseVo updateMenu(@RequestBody @Validated({ValidGroup.Update.class}) SystemMenuDTO systemMenuDTO) {
        return systemMenuService.updateMenu(systemMenuDTO);
    }

    @Override
    @AuthorityLimit(AuthorityCodeConstant.SystemManageCatalog.SystemUserRoleMenuManage.Button.select)
    public ResponseVo selectMenuPage(@RequestBody SystemMenuPageDTO systemMenuPageDTO) {
        return systemMenuService.selectMenuPage(systemMenuPageDTO);
    }

    @Override
    public ResponseVo selectMenu() {
        return systemMenuService.selectMenu();
    }
}
