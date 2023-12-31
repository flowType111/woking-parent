package com.woke.working.common.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woke.working.common.dto.common.AccessTokenAuthDTO;
import com.woke.working.common.dto.common.InterFaceAuthDTO;
import com.woke.working.common.dto.common.InterFaceAuthPage;
import com.woke.working.common.service.entity.TbInterFaceAuth;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestBody;

public interface InterFaceAuthService extends IService<TbInterFaceAuth> {

    ResponseVo addInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO);

    ResponseVo operateInterFaceAuth(String id, String type);

    ResponseVo selectInterFaceAuth(InterFaceAuthPage interFaceAuthPage);

    ResponseVo updateInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO);

    ResponseVo selectInterFaceDetails(String id);

    ResponseVo interFaceAuth(AccessTokenAuthDTO accessTokenAuthDTO);
}
