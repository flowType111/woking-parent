package com.woke.working.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.PageBean;
import com.woke.working.common.dao.OpenInterFaceDao;
import com.woke.working.common.dto.common.OpenInterFaceDTO;
import com.woke.working.common.dto.common.OpenInterFacePage;
import com.woke.working.common.entity.TbInterFaceAuth;
import com.woke.working.common.entity.TbOpenInterFace;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.service.OpenInterFaceService;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class OpenInterFaceServiceImpl implements OpenInterFaceService {

    @Autowired
    private OpenInterFaceDao openInterFaceDao;

    @Override
    public ResponseVo addOpenApi(OpenInterFaceDTO openInterFaceDTO) {
        extracted(openInterFaceDTO);
        TbOpenInterFace tbOpenInterFace;
        tbOpenInterFace = new TbOpenInterFace();
        BeanUtils.copyProperties(openInterFaceDTO, tbOpenInterFace);
        openInterFaceDao.insert(tbOpenInterFace);
        return ResponseVo.success();
    }

    private void extracted(OpenInterFaceDTO openInterFaceDTO) {
        TbOpenInterFace tbOpenInterFace = openInterFaceDao.selectOne(new LambdaQueryWrapper<TbOpenInterFace>()
                .eq(TbOpenInterFace::getInterFaceCode, openInterFaceDTO.getInterFaceCode()));
        if (Objects.nonNull(tbOpenInterFace)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_OPEN_INTERFACE_API_EXIST);
        }
    }

    @Override
    public ResponseVo deleteOpenApi(String id) {
        TbOpenInterFace tbOpenInterFace = openInterFaceDao.selectById(id);
        if (Objects.isNull(tbOpenInterFace)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_OPEN_INTERFACE_API_NOT_EXIST);
        }
        openInterFaceDao.deleteById(tbOpenInterFace);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo updateOpenApi(OpenInterFaceDTO openInterFaceDTO) {
        TbOpenInterFace tbOpenInterFace = openInterFaceDao.selectById(openInterFaceDTO.getId());
        if (Objects.isNull(tbOpenInterFace)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_OPEN_INTERFACE_API_NOT_EXIST);
        }
        if (!openInterFaceDTO.getInterFaceCode().equalsIgnoreCase(tbOpenInterFace.getInterFaceCode())) {
            extracted(openInterFaceDTO);
        }
        BeanUtils.copyProperties(openInterFaceDTO, tbOpenInterFace);
        openInterFaceDao.updateById(tbOpenInterFace);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo selectOpenApi(OpenInterFacePage openInterFacePage) {
        int total = openInterFaceDao.selectOpenApiCount(openInterFacePage);
        List<TbOpenInterFace> tbOpenInterFaces = null;
        if (total > 0) {
            tbOpenInterFaces = openInterFaceDao.selectOpenApi(openInterFacePage);
        }
        PageBean pageBean = new PageBean(openInterFacePage.getPageNum(), openInterFacePage.getPageSize(), total, tbOpenInterFaces);
        return ResponseVo.success(pageBean);
    }

    @Override
    public ResponseVo selectAll() {
        List<TbOpenInterFace> tbOpenInterFaces = openInterFaceDao.selectList(new LambdaQueryWrapper<TbOpenInterFace>()
                .eq(TbOpenInterFace::getStatus, StatusEnum.ENABLE.getCode()));
        return ResponseVo.success(tbOpenInterFaces);
    }
}
