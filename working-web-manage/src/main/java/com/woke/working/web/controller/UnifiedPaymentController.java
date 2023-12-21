package com.woke.working.web.controller;

import com.woke.working.api.web.UnifiedPaymentApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UnifiedPaymentController implements UnifiedPaymentApi {
    @Override
    public String unifiedPayment() {
        return "index.html";
    }
}
