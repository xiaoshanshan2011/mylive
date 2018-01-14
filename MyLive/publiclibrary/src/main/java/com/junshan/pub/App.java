package com.junshan.pub;

import android.support.multidex.MultiDexApplication;

/**
 * Created by chenjnshan on 2016/7/5.
 */
public class App extends MultiDexApplication {
    private static App app;
    public boolean isSlidingClose = true;//是否测滑关闭activity

    @Override
    public void onCreate() {
        super.onCreate();
        app = (App) getApplicationContext();
    }

    /**
     * 获取Application
     *
     * @return
     */
    public static App getInstance() {
        return app;
    }

}
