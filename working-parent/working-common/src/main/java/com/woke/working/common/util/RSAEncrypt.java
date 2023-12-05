package com.woke.working.common.util;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAEncrypt {

    private static final String ENCRYPT_TYPE = "RSA";

    private String defaultPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCj9rzXLlh92APmQZ67mK0cC2TAFmQaBLv2DYeFRsCt5kez07en3shXk88K7YgLPi/3frjlddmHM4nyKfXnon3a/ZHib0PLVyasBl3WGN49pui6RBTO+R6G3vhvkjWY0zXeloWgyUxd8NUDBK/9rxnjFCE9UxlTLUDQO88jLmQrBQIDAQAB";

    private String defaultPrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKP2vNcuWH3YA+ZBnruYrRwLZMAWZBoEu/YNh4VGwK3mR7PTt6feyFeTzwrtiAs+L/d+uOV12YczifIp9eeifdr9keJvQ8tXJqwGXdYY3j2m6LpEFM75Hobe+G+SNZjTNd6WhaDJTF3w1QMEr/2vGeMUIT1TGVMtQNA7zyMuZCsFAgMBAAECgYEAlq52NTp+UIhobP75JWXWEOPXnz/0loSrSCB+3th60lXCp1x2d2uhqfpIV76bO9nWsxheS2HWmw2eRFKTjmAiT9h7+S8a/paL7PAGGbW4JBWPpI+IuKvAgVorudwcKtDV3mzAAF7OGY5FNYafrx06ggFISi1ky6Bb8dhy8K0LboECQQDgfkYU721wfxiT1U+wva5ROqxuzhI2gR1CrNhHysIsp/0+yKATlxOypqPOrS9NFPzli3gfkoa/XMyEQzdYc1o5AkEAuvm5/cleJkP/QfZm5xc31bk7fLUHYVdTSt4qZKjvP5Hg/daNVsIXTZt1PxFi9cKYKREN9oKyXDXaYh/4atPHLQJBANz6j4PGFwU4pnnG7NW+MRwWVGwR/w2V1LawK4mnxbFtfzRRCnmeKi6eJ/qmsTwAH9zYh9sUQf2WnF7JR26SxckCQFIsnPyX+qPwsIfKBlG8F5L71NQrL7ItUfMOjy0sPfH/aBXHrNibduhp0W7Fcmu2eW9bCnrz1/XEyVTk3iX9K4UCQQDGGzQTIJXzmsuJlIsfYdByuYDRnHeOmYJ5p8qD0DdshhQdaEGHAFRPqlM1NQIQp25deXBZ07Tj4vS/tb8Lpglt";

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(ENCRYPT_TYPE).generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("utf-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(str.getBytes("utf-8"));
        //base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(ENCRYPT_TYPE).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    public String decryptDefault(String str) throws Exception {
        return decrypt(str, defaultPrivateKey);
    }

    public String encryptDefault(String str) throws Exception {
        return encrypt(str, defaultPublicKey);
    }

    public static void main(String[] args) {
        try {
            System.out.println(new RSAEncrypt().encryptDefault("123456"));
//            System.out.println(new RSAEncrypt().decryptDefault("IiHdAUZ9pS566RmvtfKTI50L7XjqFZnkgB3JkA+g0nyzEd+9KxNUUm6sXGqAYQRpAb3+oz4vsFaNastqcdvpmOocGWKHfkujMxGBtHG3NA7hBA9S044lcFtHBu891cCQ0vUjyIyuyAJXgYXDE3t2JzDuyBhFxVMr/RWEso63fnw="));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
