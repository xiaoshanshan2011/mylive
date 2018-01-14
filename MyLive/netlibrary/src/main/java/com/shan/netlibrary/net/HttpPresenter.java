package com.shan.netlibrary.net;

import android.content.Context;

import com.shan.netlibrary.contract.BaseView;


/**
 * HTTP请求方法
 * Created by chenjunshan on 2017-10-19.
 */

public class HttpPresenter extends HttpRequest {
    private Context mContext;
    private BaseView mView;

    public HttpPresenter(Context mContext, BaseView mView) {
        this.mContext = mContext;
        this.mView = mView;
    }
}
