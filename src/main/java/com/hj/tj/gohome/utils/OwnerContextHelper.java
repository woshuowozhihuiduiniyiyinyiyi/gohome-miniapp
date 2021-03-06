package com.hj.tj.gohome.utils;

import com.hj.tj.gohome.consts.OwnerConstants;
import com.hj.tj.gohome.entity.Owner;

import java.util.Map;

public class OwnerContextHelper {
    private static ThreadLocal<Map<String, Object>> ownerThreadLocal = new ThreadLocal<>();

    public static void putOwner(Map<String, Object> map){
        ownerThreadLocal.set(map);
    }

    public static Integer getOwnerId(){
        Map<String, Object> ownerMap = ownerThreadLocal.get();

        return Integer.parseInt(ownerMap.get(OwnerConstants.OWNER_ID).toString());
    }

    public static String getOwnerNickname(){
        Map<String, Object> ownerMap = ownerThreadLocal.get();

        return ownerMap.get(OwnerConstants.NICKNIME).toString();
    }

    public static String getFrom(){
        Map<String, Object> ownerMap = ownerThreadLocal.get();

        return ownerMap.get(OwnerConstants.FROM).toString();
    }

}
