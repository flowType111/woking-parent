package com.woke.working.api.common;

import com.woke.working.common.dto.common.QrCodeDTO;
import com.woke.working.common.dto.common.QrCodePageDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/qrcode")
public interface QrCodeApi {

    @PostMapping("/saveQrCodeData")
    ResponseVo saveQrCodeData(QrCodeDTO qrCodeDTO);

    @PostMapping("/selectQrCodePage")
    ResponseVo selectQrCodePage(QrCodePageDTO qrCodePageDTO);
}
