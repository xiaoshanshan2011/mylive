package com.junshan.pub.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * 对图片渲染
 * Created by chenjunshan on 11/6/17.
 */

public class DrawableUtils {
    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
