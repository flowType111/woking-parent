package com.woke.working.common.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.common.dto.common.OpenInterFacePage;
import com.woke.working.common.service.entity.TbOpenInterFace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpenInterFaceDao extends BaseMapper<TbOpenInterFace> {

    int selectOpenApiCount(@Param("openInterFacePage") OpenInterFacePage openInterFacePage);

    List<TbOpenInterFace> selectOpenApi(@Param("openInterFacePage") OpenInterFacePage openInterFacePage);
}
