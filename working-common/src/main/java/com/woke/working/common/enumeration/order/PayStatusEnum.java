package com.woke.working.common.enumeration.order;

public enum PayStatusEnum {

    OBLIGATION(0,"待付款"),
    PAID(1,"已付款"),
    CANCELED(3,"已取消");

    private Integer statusCode;

    private String statusName;

    PayStatusEnum(Integer statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
