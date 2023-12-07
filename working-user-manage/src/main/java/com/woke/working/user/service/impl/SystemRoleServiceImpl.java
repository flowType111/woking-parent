package com.woke.working.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.PageBean;
import com.woke.working.common.dto.user.SystemRoleDTO;
import com.woke.working.common.dto.user.SystemRolePageDTO;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.common.vo.user.SystemMenuTreeVo;
import com.woke.working.common.vo.user.SystemRoleMenuVo;
import com.woke.working.user.dao.SystemRoleDao;
import com.woke.working.user.dao.SystemRoleMenuDao;
import com.woke.working.user.entity.SystemRole;
import com.woke.working.user.entity.SystemRoleMenu;
import com.woke.working.user.service.SystemRoleService;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleDao systemRoleDao;

    @Autowired
    private SystemRoleMenuDao systemRoleMenuDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo addRole(SystemRoleDTO systemRoleDTO) {
        determine(systemRoleDTO);
        SystemRole systemRole = new SystemRole();
        BeanUtils.copyProperties(systemRoleDTO, systemRole);
        systemRoleDao.insert(systemRole);

        Optional.ofNullable(systemRoleDTO.getMenuList()).orElse(new ArrayList<>())
                .stream().forEach(menuList -> {
                    SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
                    systemRoleMenu.setRoleId(systemRole.getId());
                    systemRoleMenu.setMenuId(menuList);
                    systemRoleMenuDao.insert(systemRoleMenu);
                });
        return ResponseVo.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo deleteRole(String id) {
        SystemRole systemRole = systemRoleDao.selectById(id);
        if (Objects.isNull(systemRole)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_NOT_EXIST);
        }
        systemRole.setStatus(false);
        return ResponseVo.success(systemRoleDao.updateById(systemRole));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo updadteRole(SystemRoleDTO systemRoleDTO) {
        SystemRole systemRole = systemRoleDao.selectById(systemRoleDTO.getId());
        if (Objects.isNull(systemRole)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_NOT_EXIST);
        }
        if (!systemRole.getRoleCode().equalsIgnoreCase(systemRoleDTO.getRoleCode())) {
            List<SystemRole> systemCode = systemRoleDao.selectList(new LambdaQueryWrapper<SystemRole>()
                    .eq(SystemRole::getStatus, StatusEnum.ENABLE.getCode())
                    .eq(SystemRole::getRoleCode, systemRoleDTO.getRoleCode()));
            if (!CollectionUtils.isEmpty(systemCode)) {
                throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_CODE_EXIST);
            }
        }
        if (!systemRole.getRoleName().equalsIgnoreCase(systemRoleDTO.getRoleName())){
            List<SystemRole> systemName = systemRoleDao.selectList(new LambdaQueryWrapper<SystemRole>()
                    .eq(SystemRole::getStatus, StatusEnum.ENABLE.getCode())
                    .eq(SystemRole::getRoleCode, systemRoleDTO.getRoleName()));
            if (!CollectionUtils.isEmpty(systemName)) {
                throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_NAME_EXIST);
            }
        }
        BeanUtils.copyProperties(systemRoleDTO, systemRole);
        systemRoleDao.updateById(systemRole);

        systemRoleMenuDao.delete(new LambdaQueryWrapper<SystemRoleMenu>()
                .eq(SystemRoleMenu::getRoleId, systemRoleDTO.getId()));
        Optional.ofNullable(systemRoleDTO.getMenuList()).orElse(new ArrayList<>())
                .stream().forEach(menuList -> {
                    SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
                    systemRoleMenu.setRoleId(systemRole.getId());
                    systemRoleMenu.setMenuId(menuList);
                    systemRoleMenuDao.insert(systemRoleMenu);
                });
        return ResponseVo.success();
    }

    @Override
    public ResponseVo selectRolePage(SystemRolePageDTO systemRolePageDTO) {
        int total = systemRoleDao.selectRolePageCount(systemRolePageDTO);
        List<SystemRole> systemRoles = null;
        if (total > 0) {
            systemRoles = systemRoleDao.selectRolePage(systemRolePageDTO);
        }
        PageBean pageBean = new PageBean(systemRolePageDTO.getPageNum(), systemRolePageDTO.getPageSize(), total, systemRoles);
        return ResponseVo.success(pageBean);
    }

    @Override
    public ResponseVo selectRoleDetails(String id) {
        SystemRoleMenuVo selectRoleDetails = systemRoleDao.selectRoleDetails(id);
        Map<String, List<SystemMenuTreeVo>> groupMap = Optional.ofNullable(selectRoleDetails.getSystemMenuTreeVoList()).orElse(new ArrayList<>())
                .stream().collect(Collectors.groupingBy(x -> Optional.ofNullable(x.getParentId()).orElse("0")));
        selectRoleDetails.getSystemMenuTreeVoList().forEach(systemMenuTreeVo-> {
            systemMenuTreeVo.setCheldrenList(groupMap.get(systemMenuTreeVo.getId()));
        });
        List<SystemMenuTreeVo> collect = selectRoleDetails.getSystemMenuTreeVoList().stream().filter(systemMenuTreeVo-> StringUtils.isEmpty(systemMenuTreeVo.getParentId())).collect(Collectors.toList());
        selectRoleDetails.setSystemMenuTreeVoList(collect);
        return ResponseVo.success(selectRoleDetails);
    }

    @Override
    public ResponseVo selectRoleAll() {
        List<SystemRole> systemCode = systemRoleDao.selectList(new LambdaQueryWrapper<SystemRole>()
                .eq(SystemRole::getStatus, StatusEnum.ENABLE.getCode()));
        return ResponseVo.success(systemCode);
    }


    private void determine(SystemRoleDTO systemRoleDTO) {
        List<SystemRole> systemCode = systemRoleDao.selectList(new LambdaQueryWrapper<SystemRole>()
                .eq(SystemRole::getStatus, StatusEnum.ENABLE.getCode())
                .eq(SystemRole::getRoleCode, systemRoleDTO.getRoleCode()));
        if (!CollectionUtils.isEmpty(systemCode)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_CODE_EXIST);
        }
        List<SystemRole> systemName = systemRoleDao.selectList(new LambdaQueryWrapper<SystemRole>()
                .eq(SystemRole::getStatus, StatusEnum.ENABLE.getCode())
                .eq(SystemRole::getRoleCode, systemRoleDTO.getRoleName()));
        if (!CollectionUtils.isEmpty(systemName)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_NAME_EXIST);
        }
    }
}
