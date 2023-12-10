package com.woke.working.api.common;

import com.woke.working.common.dto.user.PayConfigDTO;
import com.woke.working.common.dto.user.PayConfigPageDTO;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pay/config")
public interface PayConfigApi {

    @RequestMapping("/addPayConfig")
    ResponseVo addPayConfig(PayConfigDTO payConfigDTO);

    @GetMapping("/deletePayConfig")
    ResponseVo deletePayConfig(String id);

    @RequestMapping("/updatePayConfig")
    ResponseVo updatePayConfig(PayConfigDTO payConfigDTO);

    @RequestMapping("/selectPayConfig")
    ResponseVo selectPayConfig(PayConfigPageDTO payConfigPageDTO);

    @RequestMapping("/selectAll")
    ResponseVo selectAll();
}
