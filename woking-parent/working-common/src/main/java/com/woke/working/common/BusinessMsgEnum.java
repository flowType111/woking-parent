package com.woke.working.common;


public enum BusinessMsgEnum {

    PARMETER_EXCEPTION(102, "参数异常!"),
    SERVICE_TIME_OUT(103, "服务调用超时！"),
    UNEXPECTED_EXCEPTION(500, "系统发生异常，请联系管理员！"),

    /**
     * user服务异常处理
     */
    WORKING_USER_ADD_MENU_EXCEPTION(10001,"未查询到对应的父节点"),

    WORKING_USER_DELETE_MENU_EXCEPTION(10003,"未查询到对应的节点"),

    WORKING_USER_EXIST_CHILD_NODE(10005,"当前节点存在子节点不能删除"),

    WORKING_USER_PERMISSION_EXIST(10002,"权限点code重复"),


    WORKING_USER_ROLE_CODE_EXIST(20001,"角色code已存在"),

    WORKING_USER_ROLE_NAME_EXIST(20002,"角色名称已存在"),

    WORKING_USER_ROLE_NOT_EXIST(20003,"角色不存在");

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
