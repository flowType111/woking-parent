package com.woke.working.common.dto.user;

import com.woke.working.common.dto.PageDTO;
import lombok.Data;

@Data
public class SystemRolePageDTO extends PageDTO {

    private String roleCode;

    private String roleName;
}
