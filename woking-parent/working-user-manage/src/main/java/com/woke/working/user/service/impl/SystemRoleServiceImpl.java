package com.woke.working.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.SystemRoleDTO;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.dao.SystemRoleDao;
import com.woke.working.user.entity.SystemRole;
import com.woke.working.user.exception.BusinessErrorException;
import com.woke.working.user.service.SystemRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleDao systemRoleDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo addRole(SystemRoleDTO systemRoleDTO) {
        determine(systemRoleDTO);
        SystemRole systemRole = new SystemRole();
        BeanUtils.copyProperties(systemRoleDTO, systemRole);
        return ResponseVo.success(systemRoleDao.insert(systemRole));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo deleteRole(String id) {
        SystemRole systemRole = systemRoleDao.selectById(id);
        if (Objects.isNull(systemRole)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_NOT_EXIST);
        }
        systemRole.setStatus(false);
        return ResponseVo.success(systemRoleDao.updateById(systemRole));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo updadteRole(SystemRoleDTO systemRoleDTO) {
        SystemRole systemRole = systemRoleDao.selectById(systemRoleDTO.getId());
        if (Objects.isNull(systemRole)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_NOT_EXIST);
        }
        if (!systemRole.getRoleCode().equalsIgnoreCase(systemRoleDTO.getRoleCode()) ||
                !systemRole.getRoleName().equalsIgnoreCase(systemRoleDTO.getRoleName())){
            determine(systemRoleDTO);
        }
        BeanUtils.copyProperties(systemRoleDTO, systemRole);
        return ResponseVo.success(systemRoleDao.updateById(systemRole));
    }


    private void determine(SystemRoleDTO systemRoleDTO) {
        List<SystemRole> systemCode = systemRoleDao.selectList(new LambdaQueryWrapper<SystemRole>()
                .eq(SystemRole::getStatus, StatusEnum.ENABLE)
                .eq(SystemRole::getRoleCode, systemRoleDTO.getRoleCode()));
        if (!CollectionUtils.isEmpty(systemCode)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_CODE_EXIST);
        }
        List<SystemRole> systemName = systemRoleDao.selectList(new LambdaQueryWrapper<SystemRole>()
                .eq(SystemRole::getStatus, StatusEnum.ENABLE)
                .eq(SystemRole::getRoleCode, systemRoleDTO.getRoleName()));
        if (!CollectionUtils.isEmpty(systemName)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ROLE_NAME_EXIST);
        }
    }
}
