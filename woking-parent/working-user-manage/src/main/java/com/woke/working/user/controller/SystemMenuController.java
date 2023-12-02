package com.woke.working.user.controller;

import com.woke.working.common.dto.SystemMenuDTO;
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

@RestController
public class SystemMenuController implements SystemMenuApi {

    @Autowired
    private SystemMenuService systemMenuService;

    @Override
    public ResponseVo addMenu(@RequestBody @Validated({ValidGroup.Insert.class}) SystemMenuDTO systemMenuDTO) {
        return systemMenuService.addMenu(systemMenuDTO);
    }

    @Override
    public ResponseVo deleteMenu(@RequestParam("id") @Valid @NotNull(message = "请选择要删除的权限点") String id) {
        return systemMenuService.deleteMenu(id);
    }

    @Override
    public ResponseVo updateMenu(@RequestBody @Validated({ValidGroup.Update.class}) SystemMenuDTO systemMenuDTO) {
        return systemMenuService.updateMenu(systemMenuDTO);
    }
}
