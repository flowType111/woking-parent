package com.woke.working.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.common.dto.user.SystemRolePageDTO;
import com.woke.working.common.vo.user.SystemRoleMenuVo;
import com.woke.working.user.entity.SystemRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleDao extends BaseMapper<SystemRole> {

    int selectRolePageCount(@Param("systemRolePageDTO") SystemRolePageDTO systemRolePageDTO);

    List<SystemRole> selectRolePage(@Param("systemRolePageDTO") SystemRolePageDTO systemRolePageDTO);

    SystemRoleMenuVo selectRoleDetails(@Param("id") String id);
}
