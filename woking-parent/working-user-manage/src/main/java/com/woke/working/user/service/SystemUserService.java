package com.woke.working.user.service;

import com.woke.working.common.dto.user.SystemUserDTO;
import com.woke.working.common.dto.user.SystemUserPageDTO;
import com.woke.working.common.valid.ValidGroup;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface SystemUserService {

    ResponseVo add(SystemUserDTO systemUserDTO);

    ResponseVo deleteUser(String id);

    ResponseVo updateUser(SystemUserDTO systemUserDTO);

    ResponseVo selectUserPage(SystemUserPageDTO systemUserPageDTO);

    ResponseVo selectUserDetails(String id);

}
