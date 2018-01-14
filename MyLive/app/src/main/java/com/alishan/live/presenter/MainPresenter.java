package com.alishan.live.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.alishan.live.contract.MainContract;
import com.alishan.live.databinding.ActivityMainBinding;
import com.junshan.pub.utils.PermissionUtis;
import com.shan.netlibrary.net.HttpPresenter;

/**
 * 首页
 * Created by chenjunshan on 2017-12-20.
 */

public class MainPresenter extends HttpPresenter implements MainContract.Presenter {
    private Context mContext;
    private MainContract.View mView;
    private ActivityMainBinding mBinding;

    public MainPresenter(Context mContext, MainContract.View mView, ActivityMainBinding mBinding) {
        super(mContext, mView);
        this.mContext = mContext;
        this.mView = mView;
        this.mBinding = mBinding;
    }


    @Override
    public void checkPsermissions() {
        String[] PERMISSIONS = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        PermissionUtis.requestPermissions((Activity) mContext, PermissionUtis.REQUESTCODE, PERMISSIONS);
    }

}
