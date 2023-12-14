package com.woke.working.common.dto.common;

import com.woke.working.common.dto.PageDTO;
import lombok.Data;

@Data
public class OpenInterFacePage extends PageDTO {

    private String interFaceCode;

    private String interFaceName;

    private String interFaceDescribe;
}
