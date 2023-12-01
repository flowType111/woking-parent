package com.woke.working.user.service.impl;

import com.woke.working.common.dto.SystemMenuDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.dao.SystemMenuDao;
import com.woke.working.user.service.SystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SystemMenuServiceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuDao systemMenuDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo addMenu(SystemMenuDTO systemMenuDTO) {
        return null;
    }
}
