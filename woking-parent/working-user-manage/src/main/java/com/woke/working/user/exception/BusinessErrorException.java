package com.woke.working.user.exception;

import com.woke.working.common.BusinessMsgEnum;
import lombok.Data;

/**
 * 自定义异常处理
 *
 */
@Data
public class BusinessErrorException extends RuntimeException{

    private static final long serialVersionUID = -7480022450501760611L;

    /**
     * 异常码
     */
    private Integer code;
    /**
     * 异常提示信息
     */
    private String message;

    public BusinessErrorException(BusinessMsgEnum businessMsgEnum) {
        this.code = businessMsgEnum.getCode();
        this.message = businessMsgEnum.getMsg();
    }
}
