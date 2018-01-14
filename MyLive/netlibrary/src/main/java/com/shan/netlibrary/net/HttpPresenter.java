package com.shan.netlibrary.net;

import android.content.Context;

import com.shan.netlibrary.bean.UsercreateactionBean;
import com.shan.netlibrary.contract.BaseView;

import java.util.Map;


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


    public static final long USERCREATEACTION = 1515936884436L;
    /**
     * 创建网易云通信ID
     * @param map
     */
    public void usercreateaction(Map<String, String> map) {
        HttpCallback callback = new HttpCallback<UsercreateactionBean>(mContext, this) {
            @Override
            protected void onSuccess(UsercreateactionBean baseBean) {
                mView.onSuccess(baseBean, USERCREATEACTION);
            }

            @Override
            protected void onFailure(Throwable e) {
                mView.onFailure(e, USERCREATEACTION);
            }
        };
        startRequest(HttpBuilder.httpService.usercreateaction(AppParams.getParams(map)), callback);
    }

}
