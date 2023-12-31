package com.woke.working.common.service.service;

import com.woke.working.common.dto.user.CheckImageDTO;
import com.woke.working.common.vo.ImageCodeVo;
import com.woke.working.common.vo.ResponseVo;

public interface ImageCodeService {

    ResponseVo<ImageCodeVo> getImageCode();

    ResponseVo verifyCode(CheckImageDTO checkImageDTO);
}
