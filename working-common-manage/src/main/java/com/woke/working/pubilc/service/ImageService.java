package com.woke.working.pubilc.service;

import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ResponseVo uploadImage(MultipartFile imageFile);
}
