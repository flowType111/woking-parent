package com.woke.working.common.dto.user;

import com.woke.working.common.valid.ValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PayConfigDTO {

    @NotNull(message = "id不能为空", groups = {ValidGroup.Update.class})
    private String id;

    @NotNull(message = "请选择对应的支付方式类型", groups = {ValidGroup.Insert.class})
    private String paymentMethod;

    @NotNull(message = "请输入支付渠道名称", groups = {ValidGroup.Insert.class})
    private String paymentName;

    private String imagePath;
}
