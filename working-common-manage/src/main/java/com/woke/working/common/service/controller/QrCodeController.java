package com.woke.working.common.service.controller;

import com.woke.working.api.common.QrCodeApi;
import com.woke.working.common.dto.common.QrCodeDTO;
import com.woke.working.common.dto.common.QrCodePageDTO;
import com.woke.working.common.service.service.QrCodeService;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class QrCodeController implements QrCodeApi {

    @Autowired
    private QrCodeService qrCodeService;

    @Override
    public ResponseVo saveQrCodeData(@RequestBody @Valid QrCodeDTO qrCodeDTO) {
        return qrCodeService.saveQrCodeData(qrCodeDTO);
    }

    @Override
    public ResponseVo selectQrCodePage(@RequestBody QrCodePageDTO qrCodePageDTO) {
        return qrCodeService.selectQrCodePage(qrCodePageDTO);
    }

    @Override
    public ResponseVo getQrCodeImage(HttpServletRequest request) {
        return qrCodeService.getQrCodeImage(request);
    }


}
