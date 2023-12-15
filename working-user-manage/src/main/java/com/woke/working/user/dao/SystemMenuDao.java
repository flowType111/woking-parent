package com.woke.working.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.vo.user.SystemMenuTreeVo;
import com.woke.working.user.entity.SysPermission;
import com.woke.working.user.entity.SystemMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMenuDao extends BaseMapper<SystemMenu> {

    List<SysPermission> selectMenu(@Param("systemMenuPageDTO") SystemMenuPageDTO systemMenuPageDTO);

    List<SystemMenuTreeVo> selectMenuTree();

    List<String> selectUserRoleMeun(@Param("accountNo") String accountNo);

	List<SysPermission> queryByUserById(@Param("userId")String userId);

	List<SysPermission> findList(@Param("menuType")Integer menuType);

	List<String> findRoleMenuList(@Param("roleId")String roleId);

	SysPermission getPermissionUrlById(String id);

	SysPermission getPermissionUrlTenant(String url);

	void setMenuLeaf(@Param("id") String id,@Param("leaf") int leaf);
}
