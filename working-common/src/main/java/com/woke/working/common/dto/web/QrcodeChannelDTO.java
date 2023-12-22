package com.woke.working.common.dto.web;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QrcodeChannelDTO extends PayChannelRequestParam {

    private String qrCodeId;
}
