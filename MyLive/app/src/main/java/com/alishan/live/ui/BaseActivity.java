package com.alishan.live.ui;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.junshan.pub.ui.activity.LibActivity;
import com.alishan.live.constant.Constants;
import com.shan.netlibrary.net.CancelRequestListener;
import com.shan.netlibrary.net.HttpBuilder;

import java.util.Locale;
import java.util.Stack;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by chenjunshan on 16-11-23.
 */

public class BaseActivity<T extends ViewDataBinding, D> extends LibActivity<T, D> implements CancelRequestListener {

    private Stack<Subscription> subscriptions = new Stack<>();

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
    }

    /**
     * 语言切换
     *
     * @param language
     */
    protected void switchLanguage(String language) {
        //设置应用语言类型
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals(Constants.EN)) {
            config.locale = Locale.ENGLISH;
        } else if (language.equals(Constants.ZH)) {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        } else if (language.equals(Constants.TZH)) {
            config.locale = Locale.TRADITIONAL_CHINESE;
        }
        resources.updateConfiguration(config, dm);
    }

    @Override
    public void cancelRequest() {
        if (subscriptions != null && subscriptions.size() > 0) {
            Subscription subscription = subscriptions.peek();
            if (subscription != null) {
                //取消Http请求
                subscription.unsubscribe();
                subscriptions.pop();//出栈
            }
        }
    }

    @Override
    public void cancelAllRequest() {
        if (subscriptions != null && subscriptions.size() > 0) {
            while (!subscriptions.isEmpty()) {
                subscriptions.peek().unsubscribe();
                subscriptions.pop();
            }
        }
    }

    public <T> void startRequest(Observable observable, Subscriber<T> subscriber) {
        Subscription subscription = HttpBuilder.getInstance().execute(observable, subscriber);
        if (subscription != null) {
            subscriptions.push(subscription);//入栈
        }
    }

    public boolean isHasNext = true;//是否还有下一页
    public int pageIndex = 1;

    @Override
    public void onRefresh() {
        super.onRefresh();
        if (lvBinding == null)
            return;
        lvBinding.xrecyclerview.setLoadingMoreEnabled(true);
        lvBinding.xrecyclerview.scrollToPosition(0);
        pageIndex = 1;
        clearData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelAllRequest();
    }

}
