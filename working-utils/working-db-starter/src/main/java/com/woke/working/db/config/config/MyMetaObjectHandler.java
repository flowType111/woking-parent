package com.woke.working.db.config.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.woke.working.common.bo.UserInfoBO;
import com.woke.working.common.util.user.UserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        UserInfoBO userInfoBO = UserInfoContext.getLoginUser();
        if (Objects.nonNull(userInfoBO)) {
            this.setFieldValByName("createUser",userInfoBO.getAccountNo(),metaObject);
            this.setFieldValByName("updateUser",userInfoBO.getAccountNo(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
        UserInfoBO userInfoBO = UserInfoContext.getLoginUser();
        if (Objects.nonNull(userInfoBO)) {
            setFieldValByName("updateUser", userInfoBO.getAccountNo(), metaObject);
        }
    }
}
