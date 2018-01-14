package com.junshan.pub.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.junshan.pub.App;
import com.junshan.pub.R;

import java.text.NumberFormat;
import java.util.List;

/**
 * 公干工具类
 * Created by chenjunshan on 17-3-30.
 */

public class PublicUtils {

    /**
     * 去除含T的时间
     *
     * @return
     */
    public static String removeT(String time) {
        if (TextUtils.isEmpty(time) || !time.contains("T")) {
            return time;
        }
        int index = time.indexOf("T");
        time = time.substring(0, index) + " " + time.substring(index + 1, time.length());
        if (time.contains(".")) {
            int index2 = time.indexOf(".");
            time = time.substring(0, index2);
        }
        return time;
    }

    /**
     * 去除含T和T后面的时间
     *
     * @return
     */
    public static String removeT2(String time) {
        if (TextUtils.isEmpty(time) || !time.contains("T")) {
            return time;
        }
        int index = time.indexOf("T");
        time = time.substring(0, index);
        return time;
    }

    /**
     * 获取月日（07-02）
     *
     * @param time
     * @return
     */
    public static String removeT3(String time) {
        String date = removeT2(time);

        if (date.length() > 5) {
            return date.substring(5, date.length());
        }
        return "";
    }

    /**
     * 去除含T的时间
     *
     * @return
     */
    public static String removeT4(String time) {
        if (TextUtils.isEmpty(time) || !time.contains("T")) {
            return time;
        }
        int index = time.indexOf("T");
        time = time.substring(0, index) + "\n" + time.substring(index + 1, time.length());
        return time;
    }

    /**
     * 去除含T和截取小数点之前的时间
     *
     * @return
     */
    public static String removeTD(String time) {
        if (TextUtils.isEmpty(time) || !time.contains(".")) {
            return time;
        }
        int index_D = time.indexOf(".");
        time = time.substring(0, index_D);
        if (!time.contains("T")) {
            return time;
        }
        int index_T = time.indexOf("T");
        time = time.substring(0, index_T) + " " + time.substring(index_T + 1, time.length());
        return time;
    }

    /**
     * 获取人民币符号
     *
     * @return
     */
    public static String getRenminbi() {
        return "¥";
    }

    /**
     * 截取T之前
     *
     * @param time
     * @return
     */
    public static String removeTDate(String time, String removeChat) {
        if (TextUtils.isEmpty(time) || !time.contains("T")) {
            return time;
        }
        time = time.substring(0, time.indexOf(removeChat));
        return time;
    }

    /**
     * 保留小数点后两位
     *
     * @return
     */
    public static String cutDecimals2(String money) {
        if (TextUtils.isEmpty(money))
            return "";
        //显示成科学计数法
        Double d = Double.valueOf(money);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String s = nf.format(d);
        //保留两位
        if (s.equals("0") || s.equals("0.0")) {
            return "0.00";
        }
        if (s.contains(".")) {
            int index = s.indexOf(".");
            String decimals = s.substring(index + 1, s.length());
            if (decimals.length() > 2) {
                return s.substring(0, index + 3);
            } else {
                return s;
            }
        } else {
            return s;
        }
    }

    /**
     * 保留小数点后两位
     *
     * @return
     */
    public static String cutDecimals2(Double money) {
        //显示成科学计数法
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String s = nf.format(money);
        //保留两位
        if (s.equals("0") || s.equals("0.0")) {
            return "0.00";
        }
        if (s.contains(".")) {
            int index = s.indexOf(".");
            String decimals = s.substring(index + 1, s.length());
            if (decimals.length() > 2) {
                return s.substring(0, index + 3);
            } else if (decimals.length() == 1) {
                return s + "0";
            } else {
                return s;
            }
        } else {
            return s + ".00";
        }
    }


    /**
     * 规则2：至少包含大小写字母及数字中的两种
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    /**
     * 根据",",String转成String数组
     *
     * @param data
     * @return
     */
    public static String[] stringToStrings(String data) {
        String[] contents = null;
        if (TextUtils.isEmpty(data)) {
            contents = new String[1];
            contents[0] = "";
            return contents;
        }
        if (data.contains("，")) {
            //将中文"，"转换成英文","
            data = data.replaceAll("，", ",");
        }
        if (data.contains(",")) {
            contents = data.split(",");
        } else {
            contents = new String[1];
            contents[0] = data;
        }
        return contents;
    }

    /**
     * 获取一定长度的String
     *
     * @param content
     * @return
     */
    public static String getTextLenght8(String content) {
        if (content == null)
            return "";
        String d = "\u3000";
        if (content.length() < 8) {
            for (int i = 0; i < 8 - content.length(); i++) {
                content = content + d;
            }
        } else {
            content = content.substring(0, 8);
        }
        return content;
    }

    /**
     * 获取一定长度的String
     *
     * @param content
     * @return
     */
    public static String getTextLenght15(String content) {
        if (content == null)
            return "";
        String d = "\u3000";
        if (content.length() < 15) {
            for (int i = 0; i < 15 - content.length(); i++) {
                content = content + d;
            }
        } else {
            content = content.substring(0, 15);
        }
        return content;
    }

    /**
     * 截取经理描述
     */
    public static String cutOutJlDetails(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        String data = "";
        if (content.contains("，")) {
            //将中文"，"转换成英文","
            content = content.replaceAll("，", ",");
        }
        if (content.contains(",")) {
            String[] contents = content.split(",");
            data = contents[0];
        } else {
            data = content;
        }

        if (data.length() > 7) {
            data = data.substring(0, 7);
        }
        return data;
    }

    public static String toMoney(double m) {
        if (m >= 10000) {
            m = m / 10000;
            return String.valueOf(m) + App.getInstance().getString(R.string.str_wan);
        } else {
            return m + "";
        }
    }

    /**
     * 判断应用是否在后台运行
     *
     * @return
     */
    public static boolean isBackground() {
        ActivityManager activityManager = (ActivityManager) App.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(App.getInstance().getPackageName())) {
                Log.i(App.getInstance().getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + App.getInstance().getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(App.getInstance().getPackageName(), "处于后台" + appProcess.processName);
                    return true;
                } else {
                    Log.i(App.getInstance().getPackageName(), "处于前台" + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    public static String addHttp(String content) {
        content = content.replaceAll("src=\"http://", "src=\"//");
        content = content.replaceAll("src=\"https://", "src=\"//");
        content = content.replaceAll("src=\"//", "src=\"http://");
        return content;
    }
}
