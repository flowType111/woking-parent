package com.woke.working.common;


public enum BusinessMsgEnum {

    PARMETER_EXCEPTION(102, "参数异常!"),
    SERVICE_TIME_OUT(103, "服务调用超时！"),
    UNEXPECTED_EXCEPTION(500, "系统发生异常，请联系管理员！"),
    AUTH_TOKEN_ERROR(500, "认证权限解析token失败"),
    NO_AUTH(401, "未登录或登录已失效！"),
    AUTHORITY_ERROR(402, "您的账号权限不足,请联系管理员！"),
    MESSAGE_ENUM_ERROR(406, "异常枚举类型错误!"),
    OTHER_LOGIN(401, "你的账号在其他地点登陆"),

    /**
     * user服务异常处理
     */
    WORKING_USER_ADD_MENU_EXCEPTION(10001, "未查询到对应的父节点"),

    WORKING_USER_DELETE_MENU_EXCEPTION(10003, "未查询到对应的节点"),

    WORKING_USER_EXIST_CHILD_NODE(10005, "当前节点存在子节点不能删除"),

    WORKING_USER_PERMISSION_EXIST(10002, "权限点code重复"),

    WORKING_USER_ACCOUNT_NO_EXIST(10006, "账号已经存在"),

    WORKING_USER_ACCOUNT_NO_NOT_EXIST(10006, "账号不存在"),

    WORKING_USER_ANALYSIS_PASSWORD_ERROR(10007, "解析密码错误"),


    WORKING_USER_PASSWORD_ERROR(10008, "密码输入错误"),

    WORKING_USER_CHECK_CODE_INVALID(10009, "验证码不存在/或过期"),

    WORKING_USER_CHECK_CODE_ERROR(10000, "验证码错误"),

    WORKING_USER_ROLE_CODE_EXIST(20001, "角色code已存在"),

    WORKING_USER_ROLE_NAME_EXIST(20002, "角色名称已存在"),

    WORKING_USER_ROLE_NOT_EXIST(20003, "角色不存在"),

    /**
     * web服务
     */
    WORKING_WEB_PAY_CHANNEL_ERROR(30001, "选择渠道失败");

    /**
     * 消息码
     */
    private Integer code;
    /**
     * 消息内容
     */
    private String msg;

    private BusinessMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
