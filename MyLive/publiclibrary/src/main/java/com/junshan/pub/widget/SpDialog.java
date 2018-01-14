package com.junshan.pub.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.junshan.pub.utils.PDUtils;

/**
 * Created by benny .
 * on 14:49.2017/4/13 .
 * function：
 */

public class SpDialog {
    public static PDUtils pdUtils;
    private static boolean iscancel = true;

    public static void dismiss() {
        if (pdUtils != null && pdUtils.isShowing()) {
            pdUtils.dismiss();
        }
    }

    public static void show(Context context) {
        pdUtils = new PDUtils(context, true);
        pdUtils.show();

    }

    public static void show(Context context, boolean isCancel) {
        iscancel = isCancel;
        pdUtils = new PDUtils(context, isCancel);
        pdUtils.setOnKeyListener(listener);
        pdUtils.show();
    }

    private static DialogInterface.OnKeyListener listener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i == KeyEvent.KEYCODE_BACK && !iscancel) {
                return true;
            }
            return false;
        }
    };


    public static void clear(){
        pdUtils = null;
    }
}
