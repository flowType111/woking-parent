package com.woke.working.user.util;

import com.google.common.base.Joiner;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.bo.TokenInfoBO;
import com.woke.working.common.util.RSAEncrypt;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.net.URLEncoder;

@Component
@Slf4j
public class TokenUtil {

    @Autowired
    private RSAEncrypt rsaEncrypt;

    public static final String SPLIT_CHAR = "###";

    public String getToken(String accountNo, String loginTimeMillis) throws Exception {
        String tokenMessage = Joiner.on(SPLIT_CHAR).join(new String[]{accountNo, loginTimeMillis});
        String encrypt = rsaEncrypt.encryptDefault(tokenMessage);
        return URLEncoder.encode(encrypt, "utf-8");
    }

    public TokenInfoBO geTokenInfoBO(String token) {
        TokenInfoBO tokenInfoBO;
        try {
            if (StringUtils.isBlank(token)) {
                throw new BusinessErrorException(BusinessMsgEnum.AUTH_TOKEN_ERROR);
            }
            String encMessage = URLDecoder.decode(token, "utf-8");
            String decrypt = rsaEncrypt.decryptDefault(encMessage);
            String[] split = decrypt.split(SPLIT_CHAR);
            tokenInfoBO = new TokenInfoBO();
            tokenInfoBO.setAccountNo(split[0]);
            tokenInfoBO.setLoginTimeMillis(split[1]);
        } catch (Exception e) {
            log.error("解析token失败：{}", e);
            throw new BusinessErrorException(BusinessMsgEnum.AUTH_TOKEN_ERROR);
        }
        return tokenInfoBO;
    }
}
