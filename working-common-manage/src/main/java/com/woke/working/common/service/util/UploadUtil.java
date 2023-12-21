package com.woke.working.common.service.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UploadUtil {
    @Value("${upload.accessKey}")
    private String accessKey;

    @Value("${upload.secretKey}")
    public String secretKey;

    @Value("${upload.bucket}")
    public String bucket;

    /**
     * 上传文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    public void uploadFile(String filePath, String fileName) {
        Configuration cfg = new Configuration(Zone.xinjiapo());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    /**
     * 上传文件
     *
     * @param bytes    文件字节数组
     * @param fileName 文件名
     */
    public String uploadFile(byte[] bytes, String fileName) {
        Configuration cfg = new Configuration(Zone.xinjiapo());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        StringBuffer prefix = null;
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            prefix.append("/").append(putRet.hash);
            log.info("参数：{}", putRet);
        } catch (Exception ex) {
            log.error("上传文件失败：{}", ex);
        }
        return prefix.toString();
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     */
    public void deleteFile(String fileName) {
        Configuration cfg = new Configuration(Zone.xinjiapo());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}