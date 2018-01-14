package com.junshan.pub.utils.download;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 用DownloadManager来实现版本更新
 */
public class DownloadService extends Service {
    private static final String TAG = DownloadService.class.getSimpleName();
    public static int notificationVisibility = DownloadManager.Request.VISIBILITY_VISIBLE;//默认显示

    public static final int HANDLE_DOWNLOAD = 0x001;
    public static final String BUNDLE_KEY_DOWNLOAD_URL = "download_url";
    public static final float UNBIND_SERVICE = 2.0F;

    private Activity activity;
    private DownloadBinder binder;
    private DownloadManager downloadManager;
    private DownloadChangeObserver downloadObserver;
    private BroadcastReceiver downLoadBroadcast;
    private ScheduledExecutorService scheduledExecutorService;

    //下载任务ID
    private long downloadId;
    private String downloadUrl;
    private OnProgressListener onProgressListener;

    public Handler downLoadHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (onProgressListener != null && HANDLE_DOWNLOAD == msg.what) {
                //被除数可以为0，除数必须大于0
                if (msg.arg1 >= 0 && msg.arg2 > 0) {
                    onProgressListener.onProgres(msg.arg1 / (float) msg.arg2);
                }
            }
        }
    };

    private Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            updateProgress();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new DownloadBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        downloadUrl = intent.getStringExtra(BUNDLE_KEY_DOWNLOAD_URL);
        Log.i(TAG, "Apk下载路径传递成功：" + downloadUrl);
        downloadApk(downloadUrl);
        return binder;
    }

    private String name = "";

    /**
     * 下载最新APK
     */
    private void downloadApk(String url) {
        try {
            String[] names;
            name = getPackageName() + ".apk";
            if (!TextUtils.isEmpty(url) && url.contains("/")) {
                names = url.split("/");
                name = names[names.length - 1];
            }
            //删除存在同名的apk
            String path = Environment.DIRECTORY_DOWNLOADS + "/" + name;
            File file = new File(Environment.getExternalStorageDirectory(), path);
            if (file.exists()) {
                file.delete();
            }
            downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            downloadObserver = new DownloadChangeObserver();

            registerContentObserver();

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            /**设置下载文件的类型*/
            request.setMimeType("application/vnd.android.package-archive");
            /**设置用于下载时的网络状态*/
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            /**设置通知栏是否可见*/
            request.setNotificationVisibility(notificationVisibility);
            /**设置漫游状态下是否可以下载*/
            request.setAllowedOverRoaming(true);
            /**如果我们希望下载的文件可以被系统的Downloads应用扫描到并管理，
             我们需要调用Request对象的setVisibleInDownloadsUi方法，传递参数true.*/
            request.setVisibleInDownloadsUi(true);
            //设置下载存放的文件夹和文件名字
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);
            Log.i(TAG, "Environment: " + Environment.DIRECTORY_DOWNLOADS);
            Log.i(TAG, "getPackageName: " + getPackageName() + "/" + name);
            /**将下载请求放入队列， return下载任务的ID*/
            downloadId = downloadManager.enqueue(request);
            registerBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
            unregisterBroadcast();
            unregisterContentObserver();
            Log.i(TAG, "下载任务服务销毁");
        }

    }

    /**
     * 注册广播
     */
    private void registerBroadcast() {
        /**注册service 广播 1.任务完成时 2.进行中的任务被点击*/
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        registerReceiver(downLoadBroadcast = new DownLoadBroadcast(), intentFilter);
    }

    /**
     * 注销广播
     */
    private void unregisterBroadcast() {
        if (downLoadBroadcast != null) {
            unregisterReceiver(downLoadBroadcast);
            downLoadBroadcast = null;
        }
    }

    /**
     * 注册ContentObserver
     */
    private void registerContentObserver() {
        /** observer download change **/
        if (downloadObserver != null) {
            getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), false, downloadObserver);
        }
    }

    /**
     * 注销ContentObserver
     */
    private void unregisterContentObserver() {
        if (downloadObserver != null) {
            getContentResolver().unregisterContentObserver(downloadObserver);
        }
    }

    /**
     * 关闭定时器，线程等操作
     */
    private void close() {
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.shutdown();
        }

        if (downLoadHandler != null) {
            downLoadHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 发送Handler消息更新进度和状态
     */
    private void updateProgress() {
        int[] bytesAndStatus = getBytesAndStatus(downloadId);
        downLoadHandler.sendMessage(downLoadHandler.obtainMessage(HANDLE_DOWNLOAD, bytesAndStatus[0], bytesAndStatus[1], bytesAndStatus[2]));
    }

    /**
     * 通过query查询下载状态，包括已下载数据大小，总大小，下载状态
     *
     * @param downloadId
     * @return
     */
    private int[] getBytesAndStatus(long downloadId) {
        int[] bytesAndStatus = new int[]{
                -1, -1, 0
        };
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor cursor = null;
        try {
            cursor = downloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                //已经下载文件大小
                bytesAndStatus[0] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //下载文件的总大小
                bytesAndStatus[1] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //下载状态
                bytesAndStatus[2] = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return bytesAndStatus;
    }

    /**
     * 绑定此DownloadService的Activity实例
     *
     * @param activity
     */
    public void setTargetActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * 接受下载完成广播
     */
    private class DownLoadBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            switch (intent.getAction()) {
                case DownloadManager.ACTION_DOWNLOAD_COMPLETE:
                    if (downloadId == downId && downId != -1 && downloadManager != null) {
                        //Uri downIdUri = downloadManager.getUriForDownloadedFile(downloadId);
                        close();
                        handler.postDelayed(runnable, 1500);
                        if (onProgressListener != null) {
                            onProgressListener.onProgres(UNBIND_SERVICE);
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            install();
        }
    };

    /**
     * 通过隐式意图调用系统安装程序安装APK
     */
    public void install() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
        String path = file.getAbsolutePath();
        Log.i(TAG, "install: " + path);
        if (!TextUtils.isEmpty(path) && path.length() > 3 && path.substring(path.length() - 3, path.length()).equals("apk")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            this.startActivity(intent);
        }
    }

    /**
     * 监听下载进度
     */
    private class DownloadChangeObserver extends ContentObserver {

        public DownloadChangeObserver() {
            super(downLoadHandler);
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }

        /**
         * 当所监听的Uri发生改变时，就会回调此方法
         *
         * @param selfChange 此值意义不大, 一般情况下该回调值false
         */
        @Override
        public void onChange(boolean selfChange) {
            scheduledExecutorService.scheduleAtFixedRate(progressRunnable, 0, 1, TimeUnit.SECONDS);
        }
    }

    public class DownloadBinder extends Binder {
        /**
         * 返回当前服务的实例
         *
         * @return
         */
        public DownloadService getService() {
            return DownloadService.this;
        }

    }

    public interface OnProgressListener {
        /**
         * 下载进度
         *
         * @param fraction 已下载/总大小
         */
        void onProgres(float fraction);
    }

    /**
     * 对外开发的方法
     *
     * @param onProgressListener
     */
    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
        unregisterContentObserver();
        Log.i(TAG, "下载任务服务销毁");
    }
}
