package com.woke.working.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.common.dto.user.SystemRolePageDTO;
import com.woke.working.user.entity.SystemRole;
import com.woke.working.user.entity.SystemRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleMenuDao extends BaseMapper<SystemRoleMenu> {


}
