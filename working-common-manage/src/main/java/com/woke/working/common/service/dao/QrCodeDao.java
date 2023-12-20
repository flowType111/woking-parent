package com.woke.working.common.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.dto.common.QrCodePageDTO;
import com.woke.working.common.service.entity.QrCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QrCodeDao extends BaseMapper<QrCode> {

    Page<QrCode> selectQrCodePage(@Param("page") Page<QrCode> page, @Param("qrCodePageDTO") QrCodePageDTO qrCodePageDTO);
}
