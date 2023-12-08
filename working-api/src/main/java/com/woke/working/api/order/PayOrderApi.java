package com.woke.working.api.order;

import com.woke.working.common.dto.order.OrderPageDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order/payment")
public interface PayOrderApi {

    @RequestMapping("/selectOrderPage")
    ResponseVo selectOrderPage(OrderPageDTO orderPageDTO);
}
