package com.woke.working.common.enumeration;

public enum StatusEnum {

    ENABLE(1,"启用"),
    DISABLE(0,"禁用");

    private Integer code;

    private String value;

    private StatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
