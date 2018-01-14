package com.junshan.pub.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * 自定义listView
 * Created by chenjunshan on 17-3-16.
 */

public class CustomListView extends LinearLayout {
    private LayoutParams params;

    public CustomListView(Context context) {
        super(context);
        init();
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void addView(View view) {
        if (view == null)
            return;
        addView(view, params);
    }

    public void addViews(List<View> views) {
        if (views == null)
            return;
        for (int i = 0; i < views.size(); i++) {
            addView(views.get(i));
        }
    }
}
