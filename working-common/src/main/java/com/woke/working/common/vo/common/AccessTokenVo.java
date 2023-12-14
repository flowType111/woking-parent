package com.woke.working.common.vo.common;

import lombok.Data;

@Data
public class AccessTokenVo {

    private String accessKey;

    private String accessToken;

    private Long expiresTime;
}
