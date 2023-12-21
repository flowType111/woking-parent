package com.woke.working.api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * h5端统一支付页面
 *
 */
@RequestMapping("/unified/payment")
public interface UnifiedPaymentApi {

    @GetMapping("/index")
    String unifiedPayment();
}
