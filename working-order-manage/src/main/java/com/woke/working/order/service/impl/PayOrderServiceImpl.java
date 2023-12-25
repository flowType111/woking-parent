package com.woke.working.order.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woke.working.common.PageBean;
import com.woke.working.common.dto.order.OrderPageDTO;
import com.woke.working.common.dto.web.PayOrderDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.order.dao.PayOrderDao;
import com.woke.working.order.entity.PayOrder;
import com.woke.working.order.service.PayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PayOrderServiceImpl extends ServiceImpl<PayOrderDao, PayOrder> implements PayOrderService {

    @Autowired
    private PayOrderDao payOrderDao;

    /**
     * 生成支付订单
     *
     * @param payOrderDTO
     */
    @Override
    public void addPayOrder(PayOrderDTO payOrderDTO) {
        PayOrder payOrder = new PayOrder();
        BeanUtils.copyProperties(payOrderDTO, payOrder);
        this.save(payOrder);
    }

    @Override
    public ResponseVo selectOrderPage(OrderPageDTO orderPageDTO) {
        Page<OrderPageDTO> page = new Page<OrderPageDTO>(orderPageDTO.getOffSet(), orderPageDTO.getPageSize());
        Page<PayOrder> pageList = payOrderDao.selectOrderPage(page, orderPageDTO);
        return ResponseVo.success(pageList);
    }

	@Override
	public ResponseVo checkOrder(String qrCodeId) {
		return ResponseVo.success(payOrderDao.checkOrder(qrCodeId));
	}
}
