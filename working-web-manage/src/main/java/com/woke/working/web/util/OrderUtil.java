package com.woke.working.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderUtil {

    /**
     * 生成订单号
     *
     * @return
     */
    public static String getOrderNumber() {
        // 获取当前日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = simpleDateFormat.format(new Date());
        // 生成随机数
        Random random = new Random();
        int randomNum = random.nextInt(8999) + 1000;
        String randomStr = String.valueOf(randomNum);
        // 生成检验code吗
        String checkCode = generateCheckCode(randomStr);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(timestamp).append(randomStr).append(checkCode);
        return stringBuffer.toString();
    }

    public static String generateCheckCode(String value) {
        int sum = 0;
        for (int i = 0; i < value.length(); i++) {
            sum += Integer.parseInt(String.valueOf(value.charAt(i)));
        }
        int checkCode = sum % 10;
        return String.valueOf(checkCode);
    }
}
