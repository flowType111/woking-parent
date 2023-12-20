package com.woke.working.common.service.service;

import com.woke.working.common.dto.common.QrCodeDTO;
import com.woke.working.common.vo.ResponseVo;

public interface QrCodeService {

    ResponseVo saveQrCodeData(QrCodeDTO qrCodeDTO);
}
