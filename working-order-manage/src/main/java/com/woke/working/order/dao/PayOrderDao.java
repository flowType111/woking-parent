package com.woke.working.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.dto.order.OrderPageDTO;
import com.woke.working.order.entity.PayOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayOrderDao extends BaseMapper<PayOrder> {

    Page<PayOrder> selectOrderPage(@Param("page") Page<OrderPageDTO> page, @Param("orderPageDTO") OrderPageDTO orderPageDTO);

	Integer checkOrder(@Param("qrCodeId")String qrCodeId);
}
