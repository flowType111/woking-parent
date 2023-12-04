package com.woke.working.common.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfoBO implements Serializable {

    private String accountNo;

    private String nickName;

    private List<String> jurisdictionCodeList;

    private String token;

    private String loginTimeMillis;
}
