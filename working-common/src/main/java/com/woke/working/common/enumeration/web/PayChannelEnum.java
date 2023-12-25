package com.woke.working.common.enumeration.web;

public enum PayChannelEnum {
    QR_CODE_CHANNEL(1,"qrCodeChannelService","二维码渠道");
    private final Integer payType;
    private final String beanName;
    private final String payDescribe;

    PayChannelEnum(Integer payType, String beanName, String payDescribe) {
        this.beanName = beanName;
        this.payType = payType;
        this.payDescribe = payDescribe;
    }

    public Integer getPayType() {
        return payType;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getPayDescribe() {
        return payDescribe;
    }
}
