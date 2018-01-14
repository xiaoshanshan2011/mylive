package com.junshan.pub.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by chenjunshan on 2016/5/27.
 */
public class BaseExpandableList extends ExpandableListView {

    public BaseExpandableList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BaseExpandableList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseExpandableList(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
