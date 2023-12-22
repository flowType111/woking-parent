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
     *
     * 外部接口处理
     *
     */
    INTERFACE_AUTH_ACCOUNT_NOT_EXIST(00001,"账号不存在白名单"),

    INTERFACE_AUTH_ACCOUNT_DISABLE(00002,"白名单账号已被禁用"),

    INTERFACE_AUTH_ANALYSIS_PASSWORD(00003,"解析encryptionKey失败"),

    INTERFACE_AUTH_PASSWORD_ERROR(0004,"encryptionKey错误"),


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

    WORKING_USER_PAY_CONFIG_EXIST(20004, "配置的支付类型已存在"),

    WORKING_USER_PAY_CONFIG_NOT_EXIST(20005, "配置的支付类型不存在"),

    /**
     * web服务
     */
    WORKING_WEB_PAY_CHANNEL_ERROR(30001, "选择渠道失败"),

    /**
     * common服务
     */
    WORKING_COMMON_IMAGE_NAME_LENGTH(40001, "图片文件名过长，最大长度为80字符"),

    WORKING_COMMON_IMAGE_FORMAT(40002, "图片文件格式错误，允许上传jpg,jpeg,png,gif格式"),

    WORKING_COMMON_IMAGE_MAX(40003, "图片文件过大，最大限制为1MB"),

    WORKING_COMMON_INTERFACE_AUTH_ACCESS_KEY_EXIST(40004,"白名单账号已存在"),

    WORKING_COMMON_INTERFACE_AUTH_ACCESS_KEY_NOT_EXIST(40005,"白名单账号不存在"),

    WORKING_COMMON_INTERFACE_AUTH_CONFIG_EXIST(40006,"白名单账号已配置当前接口code"),

    WORKING_COMMON_OPEN_INTERFACE_API_EXIST(40007,"对外接口code已存在"),

    WORKING_COMMON_OPEN_INTERFACE_API_NOT_EXIST(40008,"对外接口code不存在"),

    WORKING_COMMON_QR_CODE_ID_EXIST(40009,"二维吗id已存在"),




    WORKING_ORDER_PAY_STATUS_NOT_EXIST(50001,"当前支付码不存在待支付订单")



    ;

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
