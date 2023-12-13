package com.woke.working.common.dto;

import lombok.Data;

@Data
public class PageDTO {

    private Integer pageSize;

    private Integer pageNum;

    private Integer offSet;
}
