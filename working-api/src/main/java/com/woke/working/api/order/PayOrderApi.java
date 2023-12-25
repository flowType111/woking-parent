package com.woke.working.api.order;

import com.woke.working.common.dto.order.OrderPageDTO;
import com.woke.working.common.vo.ResponseVo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order/payment")
public interface PayOrderApi {

	@PostMapping("/selectOrderPage")
    ResponseVo selectOrderPage(OrderPageDTO orderPageDTO);
	
	@PostMapping("/checkOrder")
    ResponseVo checkOrder(String qrCodeId);
}
