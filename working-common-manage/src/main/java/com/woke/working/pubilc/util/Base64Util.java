package com.woke.working.pubilc.util;

import com.alibaba.nacos.common.codec.Base64;

public class Base64Util {

    private static final String sla = "XXXX1";

    //base64 解码
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    //base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }
}
