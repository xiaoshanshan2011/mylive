package com.alishan.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alishan.live.contract.MainContract;
import com.alishan.live.databinding.ActivityMainBinding;
import com.alishan.live.presenter.MainPresenter;
import com.alishan.live.ui.BaseActivity;
import com.shan.netlibrary.net.BaseBean;

public class MainActivity extends BaseActivity<ActivityMainBinding, Object> implements MainContract.View {
    private MainPresenter presenter;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        titleBinding.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
        presenter = new MainPresenter(this, this, mBinding);
        presenter.checkPsermissions();
    }

    @Override
    public void onSuccess(BaseBean baseBean, long mTag) {

    }

    @Override
    public void onFailure(Throwable e, long mTag) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelAllRequest();
    }
}
