package com.woke.working.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.SystemMenuDTO;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.dao.SystemMenuDao;
import com.woke.working.user.entity.SystemMenu;
import com.woke.working.user.exception.BusinessErrorException;
import com.woke.working.user.service.SystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class SystemMenuServiceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuDao systemMenuDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo addMenu(SystemMenuDTO systemMenuDTO) {
        List<SystemMenu> systemMenuList = systemMenuDao.selectList(new LambdaQueryWrapper<SystemMenu>()
                .eq(SystemMenu::getStatus, StatusEnum.ENABLE.getCode()));
        SystemMenu addMeunVo = new SystemMenu();
        // 如果上级为空默认为1层级
        if (!StringUtils.isEmpty(systemMenuDTO.getParentId())) {
            SystemMenu systemMenu = systemMenuList.stream().filter(systemMenuVo -> systemMenuVo.getId().equalsIgnoreCase(systemMenuDTO.getParentId())).findFirst().orElse(null);
            if (Objects.isNull(systemMenu)) {
                throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ADD_MENU_EXCEPTION);
            }
            int level = systemMenu.getLevel().intValue() + 1;
            addMeunVo.setLevel(level);
        } else {
            SystemMenu systemMenu = systemMenuList.stream().filter(systemMenuVo -> systemMenuVo.getMenuCode().equalsIgnoreCase(systemMenuDTO.getMenuCode())).findFirst().orElse(null);
            if (Objects.nonNull(systemMenu)) {
                throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PERMISSION_EXIST);
            }
        }
        BeanUtils.copyProperties(systemMenuDTO, addMeunVo);
        return ResponseVo.success(systemMenuDao.insert(addMeunVo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo deleteMenu(String id) {
        SystemMenu systemMenu = Optional.ofNullable(systemMenuDao.selectById(id)).orElse(new SystemMenu());
        if (Objects.isNull(systemMenu)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_DELETE_MENU_EXCEPTION);
        }
        Long existCount = systemMenuDao.selectCount(new LambdaQueryWrapper<SystemMenu>()
                .eq(SystemMenu::getParentId, id)
                .eq(SystemMenu::getStatus, StatusEnum.ENABLE));
        if (existCount > 0) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_EXIST_CHILD_NODE);
        }
        systemMenu.setStatus(false);
        return ResponseVo.success(systemMenuDao.updateById(systemMenu));
    }

    @Override
    public ResponseVo updateMenu(SystemMenuDTO systemMenuDTO) {
        SystemMenu systemMenu = systemMenuDao.selectOne(new LambdaQueryWrapper<SystemMenu>()
                .eq(SystemMenu::getId, systemMenuDTO.getId())
                .eq(SystemMenu::getStatus, StatusEnum.ENABLE.getCode()));

        if (!systemMenuDTO.getMenuCode().equalsIgnoreCase(systemMenu.getMenuCode())) {
            Long existCount = systemMenuDao.selectCount(new LambdaQueryWrapper<SystemMenu>()
                    .eq(SystemMenu::getStatus, StatusEnum.ENABLE.getCode())
                    .eq(SystemMenu::getMenuCode, systemMenuDTO.getMenuCode()));
            if (existCount > 0) {
                throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PERMISSION_EXIST);
            }
        }
        BeanUtils.copyProperties(systemMenuDTO, systemMenu);
        return ResponseVo.success(systemMenuDao.updateById(systemMenu));
    }
}
