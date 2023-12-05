package com.woke.working.common.util.user;

import com.woke.working.common.bo.UserInfoBO;

public class UserInfoContext {

    private final static ThreadLocal<UserInfoBO> userInfo = new ThreadLocal<>();

    public static UserInfoBO getLoginUser() {
        return userInfo.get();
    }

    public static void setLoginUser(UserInfoBO user) {
        userInfo.set(user);
    }

    public static void removeCurrentInfo() {
        userInfo.remove();
    }
}
