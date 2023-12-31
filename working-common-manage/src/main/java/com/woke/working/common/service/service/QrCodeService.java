package com.woke.working.common.service.service;

import com.woke.working.common.dto.common.QrCodeDTO;
import com.woke.working.common.dto.common.QrCodePageDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface QrCodeService {

    ResponseVo saveQrCodeData(QrCodeDTO qrCodeDTO);

    ResponseVo selectQrCodePage(QrCodePageDTO qrCodePageDTO);

    ResponseVo getQrCodeImage(HttpServletRequest request);
}
