package com.woke.working.api.common;

import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传api
 *
 */
@RequestMapping("/image")
public interface ImageApi {

    @PostMapping("/upload/image")
    ResponseVo uploadImage(MultipartFile imageFile);
}
