package com.woke.working.user.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.user.entity.SystemUserRole;

public interface SystemUserRoleDao extends BaseMapper<SystemUserRole> {

	List<String> queryUserRole(@Param("userid")String userid);
}
