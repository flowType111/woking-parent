package com.example.working.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseVo<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 失败消息
     */
    private String msg;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 返回的数据
     */
    private Object data;

    public static ResponseVo success() {
        ResponseVo result = new ResponseVo();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }


    public static ResponseVo success(Object data) {
        ResponseVo result = new ResponseVo();
        result.setSuccess(true);
        result.setCode(200);
        result.setData(data);
        return result;
    }


    /**
     * @param errorMsg
     * @return
     */
    public static ResponseVo fail(String errorMsg) {
        ResponseVo result = new ResponseVo();
        result.setSuccess(false);
        result.setCode(500);
        result.setMsg(errorMsg);
        return result;
    }

    public static ResponseVo fail(Integer code, String msg) {
        ResponseVo result = new ResponseVo();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
