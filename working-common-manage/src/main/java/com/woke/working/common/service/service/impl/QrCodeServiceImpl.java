package com.woke.working.common.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.constant.RedisKeyConstant;
import com.woke.working.common.dto.common.QrCodeDTO;
import com.woke.working.common.dto.common.QrCodePageDTO;
import com.woke.working.common.service.dao.QrCodeDao;
import com.woke.working.common.service.entity.PayConfig;
import com.woke.working.common.service.entity.QrCode;
import com.woke.working.common.service.service.QrCodeService;
import com.woke.working.common.service.util.IpUtils;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.redis.util.RedisUtil;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
@Slf4j
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeDao qrCodeDao;

    @Autowired
    private RedisUtil redisUtil;

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

    /**
     * 获取支付二维码
     *
     * @param request
     * @return
     */
    @Override
    public ResponseVo getQrCodeImage(HttpServletRequest request) {
        //获取IP地址
        String ipAddress = IpUtils.getIpAddr(request);
        // 设置key
        String redisKey = RedisKeyConstant.QR_CODE_KEY + ipAddress;
        // 根据ip地址获取redis缓存是不是有码
        String value = redisUtil.getString(redisKey);
        if (StringUtils.isNotBlank(value)) {
            QrCode qrCode = JSON.parseObject(value, QrCode.class);
            return ResponseVo.success(qrCode);
        }
        // 随机获取一条未匹配状态的二维码
        QrCode qrCode = qrCodeDao.selectRand();
        if (Objects.nonNull(qrCode)) {
            QrCode updateCode = new QrCode();
            updateCode.setQrCodeStatus(1);
            updateCode.setId(qrCode.getId());
            qrCodeDao.updateById(updateCode);
        }
        redisUtil.setObject(redisKey, JSON.toJSONString(qrCode), 360L);
        return ResponseVo.success(qrCode);
    }
}
