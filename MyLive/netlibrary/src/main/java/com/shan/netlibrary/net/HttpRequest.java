package com.shan.netlibrary.net;


import java.util.Stack;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by 陈俊山 on 16-10-2.
 */

public class HttpRequest implements CancelRequestListener {
    private Stack<Subscription> subscriptions = new Stack<>();

    /**
     * 取消网络请求
     */
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

    /**
     * 取消所有网络请求
     */
    @Override
    public void cancelAllRequest() {
        if (subscriptions != null && subscriptions.size() > 0) {
            while (!subscriptions.isEmpty()) {
                subscriptions.peek().unsubscribe();
                subscriptions.pop();
            }
        }
    }

    /**
     * 开始发起网络请求
     * @param observable
     * @param subscriber
     * @param <T>
     */
    public <T> void startRequest(Observable observable, Subscriber<T> subscriber) {
        Subscription subscription = HttpBuilder.getInstance().execute(observable, subscriber);
        if (subscription!=null){
            subscriptions.push(subscription);//入栈
        }
    }
}
