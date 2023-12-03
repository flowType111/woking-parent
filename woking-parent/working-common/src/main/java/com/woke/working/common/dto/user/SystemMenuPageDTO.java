package com.woke.working.common.dto.user;

import com.woke.working.common.dto.PageDTO;
import lombok.Data;

@Data
public class SystemMenuPageDTO extends PageDTO {

    private String menuName;

    private String menuCode;

    private Integer nodeType;
}
