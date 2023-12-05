package com.woke.working.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.common.dto.user.SystemUserPageDTO;
import com.woke.working.common.vo.user.SystemUserRoleVo;
import com.woke.working.user.entity.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUserDao extends BaseMapper<SystemUser> {

    int selectUserCount(@Param("systemUserPageDTO") SystemUserPageDTO systemUserPageDTO);

    List<SystemUser> selectUserPage(@Param("systemUserPageDTO") SystemUserPageDTO systemUserPageDTO);

    SystemUserRoleVo selectUserDetails(@Param("id") String id);
}
