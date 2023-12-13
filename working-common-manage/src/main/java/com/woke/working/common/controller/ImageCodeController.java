package com.woke.working.common.controller;

import com.woke.working.api.common.ImageCodeApi;
import com.woke.working.common.dto.user.CheckImageDTO;
import com.woke.working.common.service.ImageCodeService;
import com.woke.working.common.vo.ImageCodeVo;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ImageCodeController implements ImageCodeApi {


    @Autowired
    private ImageCodeService imageCodeService;

    @Override
    public ResponseVo<ImageCodeVo> getImageCode() {
        return imageCodeService.getImageCode();
    }

    @Override
    public Boolean verifyCode(@RequestBody @Valid CheckImageDTO checkImageDTO) {
        return imageCodeService.verifyCode(checkImageDTO);
    }
}
