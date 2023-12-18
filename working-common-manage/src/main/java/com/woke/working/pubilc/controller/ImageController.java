package com.woke.working.pubilc.controller;

import com.woke.working.api.common.ImageApi;
import com.woke.working.pubilc.service.ImageService;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController implements ImageApi {

    @Autowired
    private ImageService imageService;

    @Override
    public ResponseVo uploadImage(@RequestParam("file") MultipartFile imageFile) {
        return imageService.uploadImage(imageFile);
    }
}
