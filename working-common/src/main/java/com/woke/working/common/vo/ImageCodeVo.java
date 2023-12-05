package com.woke.working.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCodeVo {
    /*** 随机验证码 ***/
    private String randomCode;
    /*** 图片BASE64 ***/
    private String imageBase64;
}
