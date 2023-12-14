package com.woke.working.common.dto.common;

import lombok.Data;

@Data
public class InterFaceAuthConfig {

    private String interFaceAuthId;

    private String interFacePath;

    // 接口总共调用次数
    private Integer number;

    // 限流次数
    private Integer currentLimiting;
}
