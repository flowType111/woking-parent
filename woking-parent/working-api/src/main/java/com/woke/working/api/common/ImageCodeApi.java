package com.woke.working.api.common;

import com.woke.working.common.dto.user.CheckImageDTO;
import com.woke.working.common.vo.ImageCodeVo;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/image-code")
public interface ImageCodeApi {

    @GetMapping("/getImageCode")
    ResponseVo<ImageCodeVo> getImageCode();

    Boolean verifyCode(CheckImageDTO checkImageDTO);
}
