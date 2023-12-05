package com.woke.working.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.PageBean;
import com.woke.working.common.dto.user.SystemMenuDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.common.vo.user.SystemMenuTreeVo;
import com.woke.working.user.dao.SystemMenuDao;
import com.woke.working.user.entity.SystemMenu;
import com.woke.working.user.service.SystemMenuService;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
            SystemMenu treeVo = systemMenuList.stream().filter(systemMenuVo -> systemMenuVo.getMenuCode().equalsIgnoreCase(systemMenuDTO.getMenuCode())
                    && systemMenuVo.getLevel().equals(level)).findFirst().orElse(null);
            if (Objects.nonNull(treeVo)) {
                throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PERMISSION_EXIST);
            }
            addMeunVo.setParentCode(systemMenu.getParentCode());
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
                .eq(SystemMenu::getStatus, StatusEnum.ENABLE.getCode()));
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

    @Override
    public ResponseVo selectMenuPage(SystemMenuPageDTO systemMenuPageDTO) {
        int totalRecord = systemMenuDao.selectMenuCount(systemMenuPageDTO);
        List<SystemMenu> systemMenuList = null;
        if (totalRecord > 0) {
            systemMenuList = systemMenuDao.selectMenuPage(systemMenuPageDTO);
        }
        PageBean pageBean = new PageBean(systemMenuPageDTO.getPageNum(), systemMenuPageDTO.getPageSize(), totalRecord, systemMenuList);
        return ResponseVo.success(pageBean);
    }

    @Override
    public ResponseVo selectMenu() {
        List<SystemMenuTreeVo> systemMenuTreeVoList = systemMenuDao.selectMenuTree();
        Map<String, List<SystemMenuTreeVo>> groupMap = systemMenuTreeVoList.stream().collect(Collectors.groupingBy(x -> Optional.ofNullable(x.getParentId()).orElse("0")));
        systemMenuTreeVoList.forEach(systemMenuTreeVo -> {
            systemMenuTreeVo.setCheldrenList(groupMap.get(systemMenuTreeVo.getId()));
        });
        List<SystemMenuTreeVo> collect = systemMenuTreeVoList.stream().filter(systemMenuTreeVo -> StringUtils.isEmpty(systemMenuTreeVo.getParentId())).collect(Collectors.toList());
        return ResponseVo.success(collect);
    }
}
