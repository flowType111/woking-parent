package com.woke.working.order.controller;

import com.woke.working.api.order.PayOrderApi;
import com.woke.working.common.dto.order.OrderPageDTO;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.order.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayOrderController implements PayOrderApi {


    @Autowired
    private PayOrderService payOrderService;

    @Override
    public ResponseVo selectOrderPage(@RequestBody OrderPageDTO orderPageDTO) {
        return payOrderService.selectOrderPage(orderPageDTO);
    }

	@Override
	public ResponseVo checkOrder(String qrCodeId) {
		return payOrderService.checkOrder(qrCodeId);
	}
}
