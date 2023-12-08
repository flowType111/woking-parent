package com.woke.working.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woke.working.common.dto.web.PayOrderDTO;
import com.woke.working.order.entity.PayOrder;

public interface PayOrderService extends IService<PayOrder> {

    void addPayOrder(PayOrderDTO payOrderDTO);
}
