package com.junshan.pub.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjunshan on 12/20/17.
 */

public class HoriScrollView extends LinearLayout {
    private Context mContext;
    private HorizontalScrollView scrollView;
    private LinearLayout linearLayout;
    private int width;//屏幕的宽度
    private int textViewWidth = 0;

    public HoriScrollView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public HoriScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public HoriScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        width = getScreenWidth();
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);
        initScrollView();
    }

    /**
     * 初始化左边的View
     */
    private void initScrollView() {
        scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params1.weight = 1;
        this.addView(scrollView, params1);

        linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER | Gravity.LEFT);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        scrollView.addView(linearLayout, params2);
    }

    private List<String> data;
    private List<TextView> textViews;

    public void bindData(List<String> data) {
        this.data = data;
        if (data != null && data.size() > 0) {
            textViews = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                initLeftViewItem(data.get(i), i);
            }
        }
    }

    private TextView textView;

    /**
     * 初始化左边的View的Item
     */
    private void initLeftViewItem(String title, final int position) {
        textView = new TextView(mContext);
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(40, 0, 40, 0);
        if (position == 0) {
            textView.setTextColor(0xff0097d7);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        } else {
            textView.setTextColor(0xff666666);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        }
        textViews.add(textView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        linearLayout.addView(textView, params);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextColor(position);
                scrollView.smoothScrollTo(view.getLeft() - (scrollView.getWidth() / 2 - view.getWidth() / 2), 0);
            }
        });
    }

    /**
     * 设置TextView字体颜色
     *
     * @param position
     */
    private void setTextColor(int position) {
        if (textViews != null && data != null) {
            for (int i = position - 1; i >= 0; i--) {
                textViews.get(i).setTextColor(0xff666666);
                textViews.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            }

            for (int i = position + 1; i < data.size(); i++) {
                textViews.get(i).setTextColor(0xff666666);
                textViews.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            }

            //设置字体颜色
            textViews.get(position).setTextColor(0xff0097d7);
            //设置字体大小
            textViews.get(position).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

            /*for (int i = 0; i < data.size(); i++) {
                if (i == position) {
                    //设置字体颜色
                    textViews.get(i).setTextColor(0xff0097d7);
                    //设置字体大小
                    textViews.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
                } else {
                    textViews.get(i).setTextColor(0xff666666);
                    textViews.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
                }
            }*/
        }
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
