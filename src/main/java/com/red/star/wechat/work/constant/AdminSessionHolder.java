package com.red.star.wechat.work.constant;


import com.red.star.wechat.work.entity.Admin;

/**
 * Created by henry on 4/9/14.
 */
public class AdminSessionHolder {

    private static ThreadLocal<Admin> threadLocal = new ThreadLocal<Admin>();

    public static void put(Admin admin) {
        threadLocal.set(admin);
    }

    public static Admin get() {
        return threadLocal.get();
    }

}