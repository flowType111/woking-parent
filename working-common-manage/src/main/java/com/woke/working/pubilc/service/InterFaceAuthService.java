package com.woke.working.pubilc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woke.working.common.dto.common.InterFaceAuthDTO;
import com.woke.working.common.dto.common.InterFaceAuthPage;
import com.woke.working.pubilc.entity.TbInterFaceAuth;
import com.woke.working.common.vo.ResponseVo;

public interface InterFaceAuthService extends IService<TbInterFaceAuth> {

    ResponseVo addInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO);

    ResponseVo operateInterFaceAuth(String id, String type);

    ResponseVo selectInterFaceAuth(InterFaceAuthPage interFaceAuthPage);

    ResponseVo updateInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO);

    ResponseVo selectInterFaceDetails(String id);
}