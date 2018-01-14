package com.alishan.live.presenter;

import android.content.Context;

import com.alishan.live.contract.LoginContract;
import com.shan.netlibrary.net.HttpPresenter;

/**
 * 登录
 * Created by chenjunshan on 2018-01-14.
 */

public class LoginPresenter extends HttpPresenter implements LoginContract.Presenter {
    private Context mContext;
    private LoginContract.View mView;

    public LoginPresenter(Context mContext, LoginContract.View mView) {
        super(mContext, mView);
        this.mContext = mContext;
        this.mView = mView;
    }
}