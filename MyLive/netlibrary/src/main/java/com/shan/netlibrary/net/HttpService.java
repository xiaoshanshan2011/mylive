package com.shan.netlibrary.net;

import com.shan.netlibrary.bean.UsercreateactionBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenjunshan on 2016/8/18.
 */

public interface HttpService {
    //创建网易云通信ID
    @FormUrlEncoded
    @POST("https://api.netease.im/nimserver/user/create.action")
    Observable<UsercreateactionBean> usercreateaction(@FieldMap Map<String, String> params);


}
