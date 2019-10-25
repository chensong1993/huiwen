package com.shanghai.logistics.util;

/**
 * @author chensong
 * 重复点击
 * @date 2018/9/14 14:51
 */
public class RepeatClickUtil {
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD>0 && timeD < 800) {
            return false;
        }
        lastClickTime = time;
        return true;
    }
}
