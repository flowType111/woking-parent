package com.woke.working.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woke.working.user.dao.SystemUserRoleDao;
import com.woke.working.user.entity.SystemUserRole;
import com.woke.working.user.service.SystemUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleDao, SystemUserRole> implements SystemUserRoleService {
}
