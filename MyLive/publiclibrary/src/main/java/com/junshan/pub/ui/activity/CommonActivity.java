package com.junshan.pub.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.junshan.pub.App;
import com.junshan.pub.R;
import com.junshan.pub.manager.StatusBar;
import com.junshan.pub.widget.SlidingLayout;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by 陈俊山 on 16/8/30.
 */

public class CommonActivity extends FragmentActivity {

    public static final String FRAGMENT_CLASS = "fragment_class";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        //禁止横竖屏切换
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        StatusBar.showStatusBar(this, R.color.transparent);

        try {
            Class fragmentClass = (Class) getIntent().getSerializableExtra(FRAGMENT_CLASS);
            if (fragmentClass == null)
                return;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            fragment = (Fragment) fragmentClass.newInstance();
            if (fragment == null)
                return;
            transaction.replace(R.id.frame_common, fragment, fragmentClass.getName());
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (App.getInstance().isSlidingClose) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //qq微信新浪授权防杀死
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    protected boolean enableSliding() {
        return true;
    }
}
