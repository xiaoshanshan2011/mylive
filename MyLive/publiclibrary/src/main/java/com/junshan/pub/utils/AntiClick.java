package com.junshan.pub.utils;

/**
 * Created by benny .
 * on 17:19.2017/5/6 .
 * function：//防止重复点击事件
 */

public class AntiClick {
    private static long lastClickTime = 0;

    /**
     * 一秒点一次
     *
     * @return
     */
    public static boolean isClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime >= 1000) {
            lastClickTime = time;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 想几秒就几秒吧
     *
     * @param i
     * @return
     */
    public static boolean isClick(int i) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime >= i * 1000) {
            lastClickTime = time;
            return true;
        } else {
            return false;
        }
    }
}
