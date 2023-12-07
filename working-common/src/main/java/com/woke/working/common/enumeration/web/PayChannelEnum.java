package com.woke.working.common.enumeration.web;

public enum PayChannelEnum {
    BLOCK_CHANNEL("4","blockChannelService","卡密渠道");

    private final String payType;
    private final String beanName;
    private final String payDescribe;

    PayChannelEnum(String payType, String beanName, String payDescribe) {
        this.beanName = beanName;
        this.payType = payType;
        this.payDescribe = payDescribe;
    }

    public String getPayType() {
        return payType;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getPayDescribe() {
        return payDescribe;
    }
}
