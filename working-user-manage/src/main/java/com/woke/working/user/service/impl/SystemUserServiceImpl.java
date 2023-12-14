package com.woke.working.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.user.SystemUserDTO;
import com.woke.working.common.dto.user.SystemUserPageDTO;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.util.RSAEncrypt;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.dao.SystemUserDao;
import com.woke.working.user.dao.SystemUserRoleDao;
import com.woke.working.user.entity.SystemUserRole;
import com.woke.working.user.entity.SystemUser;
import com.woke.working.user.service.SystemUserRoleService;
import com.woke.working.user.service.SystemUserService;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	private SystemUserDao systemUserDao;
	@Autowired
	private SystemUserRoleService systemUserRoleService;
	@Autowired
	private RSAEncrypt rsaEncrypt;

	@Autowired
	private SystemUserRoleDao systemUserRoleDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseVo add(SystemUserDTO systemUserDTO) {
		// 查询用户账号是不是存在
		Long userAccountCount = systemUserDao.selectCount(
				new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getAccountNo, systemUserDTO.getAccountNo())
						.eq(SystemUser::getStatus, StatusEnum.ENABLE.getCode()));
		if (userAccountCount > 0L) {
			throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ACCOUNT_NO_EXIST);
		}
		SystemUser systemUser = new SystemUser();
		BeanUtils.copyProperties(systemUserDTO, systemUser);
		try {
			systemUser.setPassword(rsaEncrypt.encryptDefault(systemUserDTO.getPassword()));
		} catch (Exception e) {
			throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
		}
		Date date = new Date();
		systemUser.setCreateTime(date);
		systemUser.setUpdateTime(date);
		systemUserDao.insert(systemUser);
		if (!StringUtils.isEmpty(systemUserDTO.getSelectedroles())) {
			String[] roles = systemUserDTO.getSelectedroles().split(",");
			for (String string : roles) {
				SystemUserRole systemUserRole = new SystemUserRole();
				systemUserRole.setUserId(systemUser.getId());
				systemUserRole.setRoleId(string);
				systemUserRoleService.save(systemUserRole);
			}
		}
		return ResponseVo.success();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseVo deleteUser(String id) {
		SystemUser systemUser = systemUserDao.selectById(id);
		if (Objects.isNull(systemUser)) {
			throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ACCOUNT_NO_NOT_EXIST);
		}
		systemUser.setStatus(false);
		systemUserDao.deleteById(systemUser);
		return ResponseVo.success();
	}

	@Override
	public ResponseVo updateUser(SystemUserDTO systemUserDTO) {
		SystemUser systemUser = systemUserDao.selectById(systemUserDTO.getId());
		if (Objects.isNull(systemUser)) {
			throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ACCOUNT_NO_NOT_EXIST);
		}
		BeanUtils.copyProperties(systemUserDTO, systemUser);
		systemUserDao.updateById(systemUser);
		if (!StringUtils.isEmpty(systemUserDTO.getSelectedroles())) {
			systemUserRoleService
					.remove(new LambdaQueryWrapper<SystemUserRole>().eq(SystemUserRole::getUserId, systemUser.getId()));
			String[] roles = systemUserDTO.getSelectedroles().split(",");
			for (String string : roles) {
				SystemUserRole systemUserRole = new SystemUserRole();
				systemUserRole.setUserId(systemUser.getId());
				systemUserRole.setRoleId(string);
				systemUserRoleService.save(systemUserRole);
			}
		}
		return ResponseVo.success();
	}

	@Override
	public ResponseVo selectUserPage(SystemUserPageDTO systemUserPageDTO) {
		Page<SystemUser> page = new Page<SystemUser>(systemUserPageDTO.getOffSet(), systemUserPageDTO.getPageSize());
		IPage<SystemUser> pageList = systemUserDao.selectUserPage(page, systemUserPageDTO);
		return ResponseVo.success(pageList);
	}

	@Override
	public ResponseVo selectUserDetails(String id) {
		return ResponseVo.success(systemUserDao.selectUserDetails(id));
	}

	@Override
	public ResponseVo checkAccountNo(String id, String name) {
		SystemUser role = null;
		if (!StringUtils.isEmpty(id)) {
			role = systemUserDao.getAccountCodeById(id);
		}
		SystemUser newRole = systemUserDao.getAccountNoTenant(name);
		if (newRole != null) {
			if (!newRole.getId().equals(id) || role == null) {
				return ResponseVo.fail("用户账号已存在");
			}
		}
		return ResponseVo.success(true);
	}

	@Override
	public ResponseVo queryUserRole(String userid) {
		return ResponseVo.success(systemUserRoleDao.queryUserRole(userid));
	}
}
