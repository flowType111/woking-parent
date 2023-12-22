package com.woke.working.common.service.service.factory;

import com.alibaba.fastjson.JSON;
import com.woke.working.common.constant.MqKeyConstant;
import com.woke.working.common.constant.RedisKeyConstant;
import com.woke.working.common.dto.MessageDTO;
import com.woke.working.common.enumeration.web.PayChannelEnum;
import com.woke.working.common.service.config.PayChannelAbstractExecutor;
import com.woke.working.common.service.dao.QrCodeDao;
import com.woke.working.common.service.entity.QrCode;
import com.woke.working.common.service.util.IpUtils;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.redis.util.RedisUtil;
import com.woke.working.web.send.MqSenderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service("qrCodeService")
@Slf4j
public class QrCodeService extends PayChannelAbstractExecutor {

    @Autowired
    private QrCodeDao qrCodeDao;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MqSenderService mqSenderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVo execute() {
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
        redisUtil.setObject(redisKey, JSON.toJSONString(qrCode));
        //发送mq信息生成订单号
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessageType(JSON.toJSONString(qrCode));
        messageDTO.setMessageType(PayChannelEnum.QR_CODE_CHANNEL.getPayType());
        mqSenderService.sendMessage(MqKeyConstant.MqExchange.exchange, true, MqKeyConstant.RoutingKey.routingKey, JSON.toJSONString(messageDTO));
        return ResponseVo.success(qrCode);
    }
}
