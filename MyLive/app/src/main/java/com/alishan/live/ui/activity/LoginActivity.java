package com.alishan.live.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alishan.live.MainActivity;
import com.alishan.live.R;
import com.alishan.live.contract.LoginContract;
import com.alishan.live.databinding.FgLoginLayoutBinding;
import com.alishan.live.presenter.LoginPresenter;
import com.alishan.live.ui.BaseActivity;
import com.junshan.pub.utils.JitterUtils;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.shan.netlibrary.net.BaseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * Created by chenjunshan on 2018-01-14.
 */

public class LoginActivity extends BaseActivity<FgLoginLayoutBinding, Object> implements LoginContract.View {
    private LoginPresenter presenter;

    @Override
    public int bindLayout() {
        return R.layout.fg_login_layout;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();

    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
        presenter = new LoginPresenter(this, this);

    }

    @Override
    public void onSuccess(BaseBean baseBean, long mTag) {

    }

    @Override
    public void onFailure(Throwable e, long mTag) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelAllRequest();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        String user = mBinding.etUser.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            JitterUtils.edit(mBinding.etUser);
            return;
        }

        String token = "06a768b483c50b212d49b23687074c14";

        /*Map<String,String> map = new HashMap<>();
        map.put("accid",user);
        presenter.usercreateaction(map);*/


        LoginInfo info = new LoginInfo(user, token); // config...
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
                        Log.i(TAG, "onSuccess: ");
                        startActivity(MainActivity.class, null);
                    }

                    @Override
                    public void onFailed(int code) {
                        Log.i(TAG, "onFailed: ");
                    }

                    @Override
                    public void onException(Throwable exception) {
                        Log.i(TAG, "onException: " + exception.toString());
                    }
                    // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                };
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);
    }
}
