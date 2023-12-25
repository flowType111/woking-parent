package com.woke.working.common.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QrCodeMessageDTO {

    private String orderNo;

    private String qrcodeId;

    private String qrcodePath;

    private String callbackUrl;

    private Integer orderPayChannel;
}
