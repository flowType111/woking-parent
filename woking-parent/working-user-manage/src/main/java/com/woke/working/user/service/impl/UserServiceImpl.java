package com.woke.working.user.service.impl;

import com.woke.working.common.dto.user.UserDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.dao.UserDao;
import com.woke.working.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseVo add(UserDTO userDTO) {
        return null;
    }
}
