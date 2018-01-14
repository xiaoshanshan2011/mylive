package com.junshan.pub.utils;

import android.annotation.SuppressLint;
import android.app.Activity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJava实现异步加载
 * Created by chenjunshan on 11/15/17.
 *
 * @param <T>传入参数
 * @param <D>返回参数
 */

public abstract class RxJavaTask<T, D> {
    private Activity activity;

    public RxJavaTask() {
    }

    public RxJavaTask(Activity activity) {
        this.activity = activity;
    }

    private Subscriber<D> subscriber = new Subscriber<D>() {
        @Override
        public void onCompleted() {

        }

        @SuppressLint("NewApi")
        @Override
        public void onError(Throwable e) {
            if (activity != null) {
                if (!activity.isDestroyed()) {
                    onFailure(e);
                } else {
                    unsubscribe();
                }
            } else {
                onFailure(e);
            }
        }

        @SuppressLint("NewApi")
        @Override
        public void onNext(D d) {
            if (activity != null) {
                if (!activity.isDestroyed()) {
                    onSuccess(d);
                } else {
                    unsubscribe();
                }
            } else {
                onSuccess(d);
            }
        }
    };


    public void start(List<T> list) {
        Observable.from(list)
                .map(new Func1<T, D>() {
                    @Override
                    public D call(T t) {
                        return onCall(t);
                    }
                })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber);

    }

    public void start(T... list) {
        Observable.from(list)
                .map(new Func1<T, D>() {
                    @Override
                    public D call(T t) {
                        return onCall(t);
                    }
                })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber);

    }

    public void start() {
        Observable observable = Observable.create(new Observable.OnSubscribe<D>() {
            @Override
            public void call(Subscriber<? super D> subscriber) {
                subscriber.onNext(onCall(null));
                subscriber.onCompleted();
            }
        });
        observable.subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber);
    }

    /**
     * 取消订阅
     */
    public void unsubscribe() {
        subscriber.unsubscribe();
    }

    protected abstract D onCall(T t);

    protected abstract void onSuccess(D d);

    protected void onFailure(Throwable e) {
    }
}
