package com.woke.working.common.service.service.impl;

import com.woke.working.common.dto.common.QrCodeDTO;
import com.woke.working.common.service.service.QrCodeService;
import com.woke.working.common.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QrCodeServiceImpl implements QrCodeService {
    @Override
    public ResponseVo saveQrCodeData(QrCodeDTO qrCodeDTO) {
        return null;
    }
}
