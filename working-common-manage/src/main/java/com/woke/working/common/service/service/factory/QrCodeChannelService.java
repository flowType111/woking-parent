package com.woke.working.common.service.service.factory;

import com.alibaba.fastjson.JSON;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.constant.MqKeyConstant;
import com.woke.working.common.dto.MessageDTO;
import com.woke.working.common.dto.common.OpenNessPlatformDTO;
import com.woke.working.common.dto.common.QrCodeMessageDTO;
import com.woke.working.common.enumeration.web.PayChannelEnum;
import com.woke.working.common.service.config.PayChannelAbstractExecutor;
import com.woke.working.common.service.dao.QrCodeDao;
import com.woke.working.common.service.entity.QrCode;
import com.woke.working.common.service.util.OrderUtil;
import com.woke.working.common.service.util.ThreadLocalUtil;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.common.vo.common.OrderVo;
import com.woke.working.web.exception.BusinessErrorException;
import com.woke.working.web.send.MqSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 红包码渠道
 */
@Service
@Slf4j
public class QrCodeChannelService extends PayChannelAbstractExecutor {

    @Value("${pay.qrCodeUrl}")
    private String qrCodeUrl;

    @Autowired
    private QrCodeDao qrCodeDao;

    @Autowired
    private MqSenderService mqSenderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo execute() {
        OpenNessPlatformDTO openNessPlatformDTO = (OpenNessPlatformDTO) ThreadLocalUtil.getLocalVar().get("openNessPlatformDTO");
        // 随机获取一条未匹配状态的二维码
        QrCode qrCode = qrCodeDao.selectRand();
        if (Objects.isNull(qrCode)) {
            throw new BusinessErrorException(BusinessMsgEnum.WORKING_COMMON_NO_QR_CODE_IMAGE);
        }
        // 匹配成功马上更改为已匹配状态
        QrCode updateCode = new QrCode();
        updateCode.setQrCodeStatus(1);
        updateCode.setId(qrCode.getId());
        qrCodeDao.updateById(updateCode);

        // 生成订单号
        String orderNo = OrderUtil.getOrderNumber();
        QrCodeMessageDTO qrCodeMessageDTO = QrCodeMessageDTO.builder().orderNo(orderNo).callbackUrl(openNessPlatformDTO.getCallbackUrl()).qrcodeId(qrCode.getQrCodeId()).orderPayChannel(PayChannelEnum.QR_CODE_CHANNEL.getPayType()).qrcodePath(qrCode.getQrCodePath()).build();

        //发送mq消息
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(String.valueOf(PayChannelEnum.QR_CODE_CHANNEL.getPayType()));
        messageDTO.setMessage(JSON.toJSONString(qrCodeMessageDTO));
        mqSenderService.sendMessage(MqKeyConstant.MqExchange.exchange, true, MqKeyConstant.RoutingKey.routingKey, JSON.toJSONString(messageDTO));

        // 返回参数
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(orderNo);
        String url = qrCodeUrl.format("%", orderVo);
        orderVo.setPayUrl(url);
        log.info("下单返回结果：{}", JSON.toJSONString(orderVo));
        return ResponseVo.success(orderVo);
    }
}
