package com.junshan.pub.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.junshan.pub.App;

/**
 * 图片三级缓存工具类(Glide)
 * Created by chenjunshan on 16-11-22.
 */

public class ImageCacheUtils {
    /**
     * 简单加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl) || imageView == null)
            return;
        Glide.with(context).load(imageUrl.contains("http") ? imageUrl : "http:" + imageUrl).into(imageView);
    }

    /**
     * 加载图片(宽高)
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView, int width, int height) {
        if (TextUtils.isEmpty(imageUrl) || imageView == null)
            return;
        Glide.with(context).load(imageUrl.contains("http") ? imageUrl : "http:" + imageUrl).override(width, height).into(imageView);
    }

    /**
     * 加载图片(设置先加载缩略图)
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param isthumbnail 是否加载缩略图
     */
    public static void loadImage(Context context, String imageUrl, final ImageView imageView, boolean isthumbnail) {
        if (TextUtils.isEmpty(imageUrl) || imageView == null)
            return;
        if (isthumbnail)
            Glide.with(context).load(imageUrl.contains("http") ? imageUrl : "http:" + imageUrl).thumbnail(0.1f).into(imageView);
        else
            Glide.with(context).load(imageUrl.contains("http") ? imageUrl : "http:" + imageUrl).into(imageView);
    }

    /**
     * 加载图片(设置加载尺寸)
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param width     设置加载图片的宽度
     * @param height    设置加载图片的高度
     */
    public static void loadImage(Context context, String imageUrl, int width, int height, final ImageView imageView, int loadImg, int errorImg) {
        if (TextUtils.isEmpty(imageUrl) || imageView == null)
            return;
        Glide.with(context).load(imageUrl.contains("http") ? imageUrl : "http:" + imageUrl).override(width, height).placeholder(loadImg).error(errorImg).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                imageView.setImageDrawable(resource);
            }
        });
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadLocalImage(Context context, String imageUrl, final ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl) || imageView == null)
            return;
        Glide.with(context).load(imageUrl).into(imageView);
    }

    /**
     * 获取缓存图片
     * @param url
     * @return
     */
    public static Bitmap getImageCache(String url,int imageSize) {
        try {
            return Glide.with(App.getInstance())
                    .load(url)
                    .asBitmap() //必须
                    .centerCrop()
                    .into(imageSize, imageSize)
                    .get();
        } catch (Exception e) {
            return null;
        }
    }
}
