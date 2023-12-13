package com.woke.working.common.util;

import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.web.exception.BusinessErrorException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 图片上传工具类
 *
 */
public class ImageUtils {
    public static final String IMAGE_JPG = "image/jpg";

    public static final String IMAGE_JPEG = "image/jpeg";

    public static final String IMAGE_PNG = "image/png";

    public static final String IMAGE_GIF = "image/gif";

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            "jpg", "jpeg", "png", "gif"
    };

    /**
     * 图片文件名最大长度
     */
    public static final int IMAGE_FILE_NAME_LENGTH_MAX = 80;

    /**
     * 图片文件大小最大限制 1MB
     */
    public static final long IMAGE_FILE_SIZE_MAX = 1 * 1024 * 1024;

    /**
     * 图片上传
     *
     * @param baseDir   相对应用的基目录
     * @param imageFile 图片文件
     * @return 图片文件名称
     */
    public static String upload(String baseDir, MultipartFile imageFile) throws IOException {
        return upload(baseDir, imageFile, DEFAULT_ALLOWED_EXTENSION);
    }

    /**
     * 图片上传
     *
     * @param baseDir       相对应用的基目录
     * @param bufferedImage 图片
     * @return 图片文件名称
     */
    public static String upload(String baseDir, BufferedImage bufferedImage) throws IOException {
        String imageFileName = DateUtils.datePath() + "/" + UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
        File desc = getAbsoluteFile(baseDir, imageFileName);
        ImageIO.write(bufferedImage, "jpg", desc);
        String pathFileName = getPathFileName(imageFileName);
        return pathFileName;
    }

    /**
     * 图片上传
     *
     * @param baseDir          相对应用的基目录
     * @param imageFile        图片文件
     * @param allowedExtension 允许上传图片文件类型
     * @return 图片文件名称
     */
    public static String upload(String baseDir, MultipartFile imageFile, String[] allowedExtension) throws IOException {
        int imageFileNameLength = imageFile.getOriginalFilename().length();
        if (imageFileNameLength > IMAGE_FILE_NAME_LENGTH_MAX) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_IMAGE_NAME_LENGTH);
        }
        long size = imageFile.getSize();
        if (IMAGE_FILE_SIZE_MAX > 0 && size > IMAGE_FILE_SIZE_MAX) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_IMAGE_MAX);
        }
        // 校验
        assertAllowed(imageFile, allowedExtension);
        String imageFileName = extractFilename(imageFile);
        File desc = getAbsoluteFile(baseDir, imageFileName);
        imageFile.transferTo(desc);
        String pathFileName = getPathFileName(imageFileName);
        return pathFileName;
    }

    /**
     * 校验
     *
     * @param imageFile        图片文件
     * @param allowedExtension 允许上传图片文件类型
     */
    public static void assertAllowed(MultipartFile imageFile, String[] allowedExtension) {
        String extension = getExtension(imageFile);
        if (!isAllowedExtension(extension, allowedExtension)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_IMAGE_FORMAT);
        }
    }

    /**
     * 获取图片文件名称的后缀
     *
     * @param imageFile 图片文件
     * @return 后缀
     */
    public static String getExtension(MultipartFile imageFile) {
        String extension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
        if (StringUtils.isBlank(extension)) {
            extension = getExtension(imageFile.getContentType());
        }
        return extension;
    }

    public static String getExtension(String prefix) {
        switch (prefix) {
            case IMAGE_JPG:
                return "jpg";
            case IMAGE_JPEG:
                return "jpeg";
            case IMAGE_PNG:
                return "png";
            case IMAGE_GIF:
                return "gif";
            default:
                return "";
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension        上传图片文件类型
     * @param allowedExtension 允许上传图片文件类型
     * @return true/false
     */
    public static boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 编码文件名
     */
    public static String extractFilename(MultipartFile imageFile) {
        String extension = getExtension(imageFile);
        String imageFileName = DateUtils.datePath() + "/" + UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;
        return imageFileName;
    }

    private static File getAbsoluteFile(String uploadDir, String imageFileName) {
        File desc = new File(uploadDir + File.separator + imageFileName);
        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc.isAbsolute() ? desc : desc.getAbsoluteFile();
    }

    private static String getPathFileName(String imageFileName) {
        String pathFileName = "/" + imageFileName;
        return pathFileName;
    }

}
