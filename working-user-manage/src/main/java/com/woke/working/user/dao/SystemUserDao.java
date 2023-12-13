package com.woke.working.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.dto.user.SystemUserPageDTO;
import com.woke.working.common.vo.user.SystemUserRoleVo;
import com.woke.working.user.entity.SystemUser;
import org.apache.ibatis.annotations.Param;

public interface SystemUserDao extends BaseMapper<SystemUser> {

    Page<SystemUser> selectUserPage(@Param("page") Page<SystemUser> page, @Param("systemUserPageDTO") SystemUserPageDTO systemUserPageDTO);

    SystemUserRoleVo selectUserDetails(@Param("id") String id);

	SystemUser getAccountCodeById(String id);

	SystemUser getAccountNoTenant(String name);
}
