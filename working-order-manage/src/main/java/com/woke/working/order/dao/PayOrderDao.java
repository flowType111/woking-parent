package com.woke.working.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woke.working.common.dto.order.OrderPageDTO;
import com.woke.working.order.entity.PayOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayOrderDao extends BaseMapper<PayOrder> {

    int selectOrderCount(@Param("orderPageDTO") OrderPageDTO orderPageDTO);

    List<PayOrder> selectOrderPage(@Param("orderPageDTO") OrderPageDTO orderPageDTO);
}
