package com.woke.working.common.dto.common;

import com.woke.working.common.dto.PageDTO;
import lombok.Data;

@Data
public class QrCodePageDTO extends PageDTO {

    private Integer qrCodeStatus;

    private String qrCodeId;
}
