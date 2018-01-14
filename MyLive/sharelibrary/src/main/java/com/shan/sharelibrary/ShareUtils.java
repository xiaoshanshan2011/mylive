package com.shan.sharelibrary;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 分享工具
 * Created by chenjunshan on 2017-3-3.
 */

public class ShareUtils {
    public static final int WEIXINID = 1;
    public static final int WEIXINSECRET = 2;
    public static final int SINAWEIBOKEY = 3;
    public static final int SINAWEIBOSECRET = 4;
    public static final int SINAWEIBOREDIRECTURL = 5;
    public static final int QQZONEKEY = 7;
    public static final int QQZONEID = 6;
    private static final String TAG = "ShareUtils";
    private static UMShareAPI umShareAPI;

    /**
     * 在Application中初始化分享
     *
     * @param context
     */
    public static void initShare(Context context, SparseArray<String> keySecret) {
        umShareAPI = UMShareAPI.get(context);
        initKeySecrit(keySecret);
    }

    private static void initKeySecrit(SparseArray<String> keySecret) {
        if (keySecret != null) {
            PlatformConfig.setWeixin(keySecret.get(WEIXINID), keySecret.get(WEIXINSECRET));
            PlatformConfig.setSinaWeibo(keySecret.get(SINAWEIBOKEY), keySecret.get(SINAWEIBOSECRET), keySecret.get(SINAWEIBOREDIRECTURL));
            PlatformConfig.setQQZone(keySecret.get(QQZONEID), keySecret.get(QQZONEKEY));
        }
    }

    /**
     * 面板分享
     *
     * @param activity
     * @param shareBean
     */
    public static void startShare(Activity activity, ShareBean shareBean, UMShareListener uMShareListener) {
        new ShareAction(activity)
                //.setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA)
                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE, SHARE_MEDIA.QQ)
                .setCallback(uMShareListener)
                .withMedia(shareBean.getUmWeb())
                .open();
    }

    /**
     * 直接分享
     *
     * @param activity
     * @param shareBean
     * @param shareMedia
     */
    public static void startShare(Activity activity, ShareBean shareBean, SHARE_MEDIA shareMedia, UMShareListener uMShareListener) {
        new ShareAction(activity).setPlatform(shareMedia)
                .setCallback(uMShareListener)
                .withMedia(shareBean.getUmWeb())
                .share();
    }

    /**
     * 登录
     *
     * @param activity
     * @param shareMedia
     * @param listener
     */
    public static void login(Activity activity, SHARE_MEDIA shareMedia, UMAuthListener listener) {
        umShareAPI.getPlatformInfo(activity, shareMedia, listener);
    }

    /**
     * 删除认证，每次都要授权才能登录
     * @param activity
     * @param shareMedia
     * @param listener
     */
    public static void delete(Activity activity, SHARE_MEDIA shareMedia, UMAuthListener listener) {
        umShareAPI.deleteOauth(activity, shareMedia, listener);
    }
}
