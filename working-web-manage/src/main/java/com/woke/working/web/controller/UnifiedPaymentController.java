package com.woke.working.web.controller;

import com.woke.working.api.web.UnifiedPaymentApi;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UnifiedPaymentController implements UnifiedPaymentApi {

    @Override
    public ModelAndView unifiedPayment() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
