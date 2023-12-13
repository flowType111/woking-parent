package com.woke.working.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.common.dto.common.InterFaceAuthPage;
import com.woke.working.common.entity.TbInterFaceAuth;
import com.woke.working.common.vo.common.InterFaceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InterFaceAuthDao extends BaseMapper<TbInterFaceAuth> {

    int selectInterFaceAuthCount(@Param("interFaceAuthPage") InterFaceAuthPage interFaceAuthPage);

    List<TbInterFaceAuth> selectInterFaceAuth(@Param("interFaceAuthPage") InterFaceAuthPage interFaceAuthPage);

    InterFaceVo selectInterFaceDetails(@Param("id") String id);
}
