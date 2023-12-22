package com.woke.working.common.enumeration.order;


public enum MessageEnum {

    QR_CODE_MESSAGE("1","qrCodeMessageService","二维码渠道");

    private final String payType;
    private final String beanName;
    private final String payDescribe;

    MessageEnum(String payType, String beanName, String payDescribe) {
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
