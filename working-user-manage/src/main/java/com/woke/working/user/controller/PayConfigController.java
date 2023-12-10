package com.woke.working.user.controller;

import com.woke.working.api.common.PayConfigApi;
import com.woke.working.common.dto.user.PayConfigDTO;
import com.woke.working.common.dto.user.PayConfigPageDTO;
import com.woke.working.common.valid.ValidGroup;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.user.service.PayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayConfigController implements PayConfigApi {

    @Autowired
    private PayConfigService payConfigService;

    @Override
    public ResponseVo addPayConfig(@RequestBody @Validated({ValidGroup.Insert.class}) PayConfigDTO payConfigDTO) {
        return payConfigService.addPayConfig(payConfigDTO);
    }

    @Override
    public ResponseVo deletePayConfig(@RequestParam("id") String id) {
        return payConfigService.deletePayConfig(id);
    }

    @Override
    public ResponseVo updatePayConfig(@RequestBody @Validated({ValidGroup.Update.class}) PayConfigDTO payConfigDTO) {
        return payConfigService.updatePayConfig(payConfigDTO);
    }

    @Override
    public ResponseVo selectPayConfig(@RequestBody PayConfigPageDTO payConfigPageDTO) {
        return payConfigService.selectPayConfig(payConfigPageDTO);
    }

    @Override
    public ResponseVo selectAll() {
        return payConfigService.selectAll();
    }
}
