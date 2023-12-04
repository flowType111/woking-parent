package com.woke.working.common.vo.user;

import lombok.Data;

import java.util.List;

@Data
public class SystemUserRoleVo {

    private String id;

    private String accountNo;

    private String nickName;

    private List<SystemRoleVo> roleVoList;
}
