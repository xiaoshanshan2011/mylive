package com.junshan.pub.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

/**
 * 获取照片工具
 * Created by chenjunshan on 17-6-8.
 */

public class PhotoUtils {
    public static final int CHOOSE_PHOTO_REQUEST = 111;
    public static final int TAKE_PHOTO_REQUEST = 211;
    public static final int File_REQUEST = 311;
    public static Uri imageUri;
    private int flag;
    public static String FLAG = "flag";

    /**
     * 跳转到相册
     */
    public static void toHhotoAlbum(Fragment fragment) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        fragment.startActivityForResult(intent, CHOOSE_PHOTO_REQUEST);
    }

    /**
     * 跳转到相机
     */
    public static void toCamera(Fragment fragment) {
        imageUri = createImageUri(fragment.getActivity());
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
        fragment.startActivityForResult(intent, TAKE_PHOTO_REQUEST);
    }

    /**
     * 跳转到相册
     */
    public static void toHhotoAlbum(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, CHOOSE_PHOTO_REQUEST);
    }

    /**
     * 跳转到相机
     */
    public static void toCamera(Activity activity) {
        imageUri = createImageUri(activity);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
        activity.startActivityForResult(intent, TAKE_PHOTO_REQUEST);
    }

    /**
     * 创建图片
     *
     * @param context
     * @return
     */
    private static Uri createImageUri(Context context) {
        String name = "takePhoto" + System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, name);
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        return uri;
    }

    /**
     * 删除图片
     *
     * @param context
     * @param uri
     */
    public static void delteImageUri(Context context, Uri uri) {
        if (uri == null)
            return;
        context.getContentResolver().delete(uri, null, null);
    }

    public static String getImagePath(Context context, Uri uri) {
        try {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                return path;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
