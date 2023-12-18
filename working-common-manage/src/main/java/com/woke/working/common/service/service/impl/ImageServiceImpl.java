package com.woke.working.common.service.service.impl;

import com.woke.working.common.service.service.ImageService;
import com.woke.working.common.service.util.ImageUtils;
import com.woke.working.common.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.image.path}")
    private String path;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.image.domain}")
    private String domain;

    /**
     * 资源映射路径前缀
     */
    @Value("${file.image.prefix}")
    private String prefix;

    @Override
    public ResponseVo uploadImage(MultipartFile imageFile) {
        String address;
        try {
            address = ImageUtils.upload(path, imageFile);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
        //总路径
        String url = domain + prefix + address;
        String name = imageFile.getOriginalFilename();
        String extension = ImageUtils.getExtension(imageFile);
        //扩展名
        System.out.println("extension = " + extension);
        //名称
        System.out.println("name = " + name);
        //上传文件存储在本地的根路径
        System.out.println("path = " + path);
        // 域名或本机访问地址
        System.out.println("domain = " + domain);
        //资源映射路径前缀
        System.out.println("prefix = " + prefix);
        //这里可以将需要的字段保存至数据库
        return ResponseVo.success(url);
    }
}
