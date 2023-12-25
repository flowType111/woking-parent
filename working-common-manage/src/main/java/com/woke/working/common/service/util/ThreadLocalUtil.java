package com.woke.working.common.service.util;

import java.util.Map;

public class ThreadLocalUtil {

    private static ThreadLocal<Map<String, Object>> localVar = new ThreadLocal<Map<String, Object>>();


    public static Map<String, Object> getLocalVar() {
        return localVar.get();
    }

    public static void setLocalVar(Map<String, Object> map){
        localVar.set(map);
    }

    public static void removeLocalVar(){
        localVar.remove();
    }
}
