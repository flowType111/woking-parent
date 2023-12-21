package com.woke.working.common.service.service.impl;

import com.woke.working.common.service.service.ImageService;
import com.woke.working.common.service.util.UploadUtil;
import com.woke.working.common.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    private UploadUtil uploadFile;

    @Override
    public ResponseVo uploadImage(MultipartFile imageFile) {
        //获取原始文件名
        String originalFilename = imageFile.getOriginalFilename();
        //获取图片名后缀
        String ext = "." + originalFilename.split("\\.")[1];
        String returnName = null;
        try {
            //使用UUID生成图片名，防止名称一样
            UUID id = UUID.randomUUID();
            String idd = id.toString().split("-")[0];//生成8位数长度的随机十六进制
            String fileName = idd.replace("-", "") + ext;
            returnName = uploadFile.uploadFile(imageFile.getBytes(), fileName); //图片上传成功
        } catch (Exception exception) {
            log.error("上传文件失败：{}", exception);
        }
        return ResponseVo.success(returnName);
    }
}
