package com.woke.working.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.dto.user.SystemRolePageDTO;
import com.woke.working.common.vo.user.SystemRoleMenuVo;
import com.woke.working.user.entity.SystemRole;
import org.apache.ibatis.annotations.Param;

public interface SystemRoleDao extends BaseMapper<SystemRole> {

	Page<SystemRole> selectRolePage(@Param("page") Page<SystemRole> page, SystemRolePageDTO systemRolePageDTO);

    SystemRoleMenuVo selectRoleDetails(@Param("id") String id);

	SystemRole getRoleCodeById(@Param("id") String id);

	SystemRole getRoleNoTenant(@Param("roleCode")String roleCode);
}
