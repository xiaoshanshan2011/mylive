package com.shan.netlibrary.net;

import com.junshan.pub.utils.LogUtils;
import com.shan.netlibrary.bean.LoginBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by benny .
 * on 8:53.2017/3/22 .
 * function：
 */

public class AppParams {

    private static LoginBean loginBean = null;
    private static String emptyUserid = "00000000-0000-0000-0000-000000000000";

    /**
     * 如果需要认证就调用该方法
     */
    public static Map<String, String> getParams(Map<String, String> map) {
        try {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


    /**
     * json格式log
     */
    public static void getJsonLog(Map<String, String> map) {
        if (map == null) return;
        JSONObject jsonObect = new JSONObject();
        Iterator iter = map.entrySet().iterator();
        try {
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                jsonObect.put((String) key, val);
            }
            LogUtils.i("===PostBean===" + jsonObect.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
