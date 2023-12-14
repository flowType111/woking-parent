package com.woke.working.common.vo.common;

import lombok.Data;

import java.util.List;

@Data
public class InterFaceVo {

    private String id;

    private String accessKey;

    private List<InterFaceConfigVo> interFaceConfigVos;
}
