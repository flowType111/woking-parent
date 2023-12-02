package com.woke.working.user.service.impl;

import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.SystemMenuDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.dao.SystemMenuDao;
import com.woke.working.user.entity.SystemMenu;
import com.woke.working.user.exception.BusinessErrorException;
import com.woke.working.user.service.SystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class SystemMenuServiceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuDao systemMenuDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo addMenu(SystemMenuDTO systemMenuDTO) {
        SystemMenu systemMenu = new SystemMenu();
        // 如果上级为空默认为1层级
        if (StringUtils.isNotEmpty(systemMenuDTO.getParentId())) {
            systemMenu = systemMenuDao.selectById(systemMenuDTO.getParentId());
            if (Objects.isNull(systemMenu)) {
                throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ADD_MENU_EXCEPTION);
            }
            systemMenu.setLevel(systemMenu.getLevel().intValue() + 1);
        }
        BeanUtils.copyProperties(systemMenuDTO, systemMenu);
        return ResponseVo.success(systemMenuDao.insert(systemMenu));
    }
}
