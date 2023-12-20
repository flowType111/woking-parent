package com.woke.working.common.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.dto.common.QrCodeDTO;
import com.woke.working.common.dto.common.QrCodePageDTO;
import com.woke.working.common.service.dao.QrCodeDao;
import com.woke.working.common.service.entity.PayConfig;
import com.woke.working.common.service.entity.QrCode;
import com.woke.working.common.service.service.QrCodeService;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeDao qrCodeDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo saveQrCodeData(QrCodeDTO qrCodeDTO) {
        // 查询当前码code是不是已经存在
        QrCode qrCode = qrCodeDao.selectOne(new LambdaQueryWrapper<QrCode>()
                .eq(QrCode::getQrCodeId,qrCodeDTO.getQrCodeId()));
        if (Objects.nonNull(qrCode)){
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_QR_CODE_ID_EXIST);
        }
        qrCode = new QrCode();
        BeanUtils.copyProperties(qrCodeDTO, qrCode);
        qrCodeDao.insert(qrCode);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo selectQrCodePage(QrCodePageDTO qrCodePageDTO) {
        Page<QrCode> page = new Page<QrCode>(qrCodePageDTO.getOffSet(), qrCodePageDTO.getPageSize());
        Page<QrCode> payConfigPage = qrCodeDao.selectQrCodePage(page,qrCodePageDTO);
        return ResponseVo.success(payConfigPage);
    }
}
