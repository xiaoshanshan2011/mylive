package com.junshan.pub.utils;

import android.content.Context;
import android.util.TypedValue;

import com.junshan.pub.App;

/**
 * 单位转换工具类
 * Created by chenjunshan on 2016/7/31.
 */

public class DensityUtils {
    /**
     * dp转px
     *
     * @return
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, App.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @return
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, App.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @return
     */
    public static float px2dp(float pxVal) {
        final float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(float pxVal) {
        return (pxVal / App.getInstance().getResources().getDisplayMetrics().scaledDensity);
    }

    public static float dip2px(Context context, float textSize) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (textSize * scale + 0.5f);
    }
}
