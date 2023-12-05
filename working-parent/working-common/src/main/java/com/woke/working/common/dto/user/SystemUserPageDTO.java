package com.woke.working.common.dto.user;

import com.woke.working.common.dto.PageDTO;
import lombok.Data;

@Data
public class SystemUserPageDTO extends PageDTO {

    private String accountNo;

    private String nikeName;
}
