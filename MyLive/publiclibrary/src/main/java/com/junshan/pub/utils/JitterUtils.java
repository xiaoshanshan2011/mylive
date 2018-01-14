package com.junshan.pub.utils;

import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.junshan.pub.App;
import com.junshan.pub.R;

/**
 * Created by benny .
 * on 10:54.2017/6/8 .
 * function：抖动功能
 */

public class JitterUtils {
    private static Animation shake;
    public static void JitterUtils() {
        //为组件设置一个抖动效果
         shake = AnimationUtils.loadAnimation(App.getInstance(), R.anim.shake);
    }

    public static void edit(final EditText editText) {
        JitterUtils();
        editText.startAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editText.setHintTextColor(Color.parseColor("#f0f1301e"));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                editText.setHintTextColor(Color.parseColor("#999999"));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public static void text(final TextView textView) {
        JitterUtils();
        textView.startAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textView.setHintTextColor(Color.parseColor("#f0f1301e"));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setHintTextColor(Color.parseColor("#999999"));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
