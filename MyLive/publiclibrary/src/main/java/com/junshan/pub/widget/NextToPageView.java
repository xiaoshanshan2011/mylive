package com.junshan.pub.widget;

import android.animation.Animator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.junshan.pub.R;
import com.junshan.pub.databinding.NextToPageViewLayoutBinding;

/**
 * Created by chenjunshan on 12/29/17.
 */

public class NextToPageView extends LinearLayout {
    private Context mContext;
    private NextToPageViewLayoutBinding mBinding;

    public NextToPageView(Context context) {
        super(context);
        mContext = context;
        initView();
        initEvent();
    }

    public NextToPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initEvent();
    }

    public NextToPageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initEvent();
    }

    private int clickY = 0;

    private void initEvent() {
        mBinding.toNext.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    clickY = (int) event.getY();
                    mBinding.flPage1.setVisibility(VISIBLE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mBinding.flPage2.setVisibility(VISIBLE);
                    mBinding.tvB.setHeight(0);
                    mBinding.tvB.invalidate();
                    YoYo.with(Techniques.BounceInUp)
                            .duration(1000)
                            .repeat(0).playOn(mBinding.flPage2);

                    YoYo.with(Techniques.BounceInUp)
                            .duration(1000)
                            .onEnd(new YoYo.AnimatorCallback() {
                                @Override
                                public void call(Animator animator) {
                                    mBinding.flPage1.setVisibility(GONE);
                                }
                            })
                            .repeat(0).playOn(mBinding.flPage1);
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    int curClickY = (int) event.getY();//点击的位置
                    int height = clickY - curClickY;
                    mBinding.tvB.setHeight(height);
                    mBinding.tvB.invalidate();
                }
                return true;
            }
        });
    }

    private void initView() {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.next_to_page_view_layout, this, false);
        this.addView(mBinding.getRoot());
    }
}
