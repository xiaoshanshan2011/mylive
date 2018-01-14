package com.junshan.pub.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junshan.pub.R;


/**
 * Created by benny .
 * Time on 2017/3/14 .
 * 描述：上面是图片，下面是文字的控件
 */

public class ItemImageText extends LinearLayout {
    private TypedArray array;
    private Drawable itemImage;
    private int itemColor;
    private String itemText;
    private int itemSize;
    private TextView tv_name;
    private ImageView iv_logo;
    OnClickListener OnClickImage;
    private LinearLayout item_layout;

    public ItemImageText(Context context) {
        super(context);

    }

    public ItemImageText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ItemImageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        array = context.obtainStyledAttributes(attrs, R.styleable.ItemImageTextStyleable);
        itemText = array.getString(R.styleable.ItemImageTextStyleable_item_text);
        itemColor = array.getColor(R.styleable.ItemImageTextStyleable_item_color, Color.parseColor("#333333"));
        itemSize = array.getInteger(R.styleable.ItemImageTextStyleable_item_size, 15);
        itemImage = array.getDrawable(R.styleable.ItemImageTextStyleable_item_image);
        initInputView(array);
        array.recycle();
        inflate(context, R.layout.item_tv_image_layout, this);
        item_layout = (LinearLayout) findViewById(R.id.ll_layout);
        tv_name = (TextView) findViewById(R.id.item_name);
        iv_logo = (ImageView) findViewById(R.id.item_logo);
        setItemText(itemText);
        setItemTextSize(itemSize);
        setItemTextColor(itemColor);
      //  setItemImage(itemImage);
        item_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickImage(this);
            }
        });
    }

    private void initInputView(TypedArray array) {

    }

    public void setOnClickImage(OnClickListener onClikImage) {
        OnClickImage = onClikImage;
    }

    public void setItemText(String itemText) {
        if (!TextUtils.isEmpty(itemText)) {
            tv_name.setText(itemText);
        }
    }

    public void setItemTextSize(int itemSize) {
        if (itemSize != 0) {
            tv_name.setTextSize(itemSize);
        }
    }

    public void setItemTextColor(int itemColor) {
        tv_name.setTextColor(itemColor);
    }

    public void setItemImage(Drawable itemImage) {
        if (itemImage != null) iv_logo.setImageDrawable(itemImage);
        ViewGroup.LayoutParams lp = iv_logo.getLayoutParams();
        lp.width = LayoutParams.WRAP_CONTENT;
        lp.height = LayoutParams.WRAP_CONTENT;
        iv_logo.setLayoutParams(lp);
    }


}
