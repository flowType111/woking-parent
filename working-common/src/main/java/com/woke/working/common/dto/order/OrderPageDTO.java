package com.woke.working.common.dto.order;

import com.woke.working.common.dto.PageDTO;
import lombok.Data;

@Data
public class OrderPageDTO extends PageDTO {

    private String orderNo;

    private Integer channel;

    private Integer payType;
}
