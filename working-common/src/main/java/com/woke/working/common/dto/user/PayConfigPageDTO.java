package com.woke.working.common.dto.user;

import com.woke.working.common.dto.PageDTO;
import lombok.Data;

@Data
public class PayConfigPageDTO extends PageDTO {

    private String paymentMethod;

    private String paymentName;

}
