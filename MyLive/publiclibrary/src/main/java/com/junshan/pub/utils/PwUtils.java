package com.junshan.pub.utils;

/**
 * Created by benny .
 * on 21:11.2017/4/10 .
 * function：
 */

public class PwUtils {
    /**
     * 纯字母
     *
     * @param fstrData
     * @return
     */
    public static boolean isChar(String fstrData) {
        String regPw = "/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$/";
        return fstrData.matches(regPw);
    }

    /**
     * 纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                ToastUtils.toast("交易密码不能为纯数字");
                return false;
            }
        }
        return true;
    }
}
