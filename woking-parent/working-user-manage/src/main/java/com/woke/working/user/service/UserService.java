package com.woke.working.user.service;

import com.woke.working.common.dto.user.UserDTO;
import com.woke.working.common.vo.ResponseVo;

public interface UserService {

    ResponseVo add(UserDTO userDTO);

}
