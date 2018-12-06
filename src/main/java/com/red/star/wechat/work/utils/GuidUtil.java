package com.red.star.wechat.work.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: macalline-work-server
 * @Package: com.red.star.macalline.work.utils
 * @Description: 全局唯一标识符
 * @Author: AMGuo
 * @CreateDate: 2018/7/2 下午6:45
 * @Version: 1.0
 */
public class GuidUtil {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    private static final AtomicInteger atomicInteger = new AtomicInteger(1000000);


    public static synchronized String generate() {
        atomicInteger.getAndIncrement();
        int i = atomicInteger.get();
        String date = simpleDateFormat.format(new Date());
        return date + i;
    }
}
