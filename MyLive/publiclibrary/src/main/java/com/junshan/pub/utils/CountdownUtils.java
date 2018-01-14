package com.junshan.pub.utils;

import android.os.CountDownTimer;

/**
 * Created by benny .
 * on 16:18.2017/4/17 .
 * function：倒计时。。。。操作
 */

public class CountdownUtils extends CountDownTimer {
    onFinishClick onFinishClick;
    //传入值到计时
    public CountdownUtils(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }


    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        //时间到了就操作
        onFinishClick.onClick();
    }

    public interface onFinishClick{
        void onClick();
    }

    public CountdownUtils.onFinishClick getOnFinishClick() {
        return onFinishClick;
    }

    public void setOnFinishClick(CountdownUtils.onFinishClick onFinishClick) {
        this.onFinishClick = onFinishClick;
    }
}
