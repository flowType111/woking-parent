package com.woke.working.user.service;

import com.woke.working.common.dto.user.CheckImageDTO;
import com.woke.working.common.vo.ImageCodeVo;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ImageCodeService {

    ResponseVo<ImageCodeVo> getImageCode();

    Boolean verifyCode(CheckImageDTO checkImageDTO);
}
