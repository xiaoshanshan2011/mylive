package com.junshan.pub.manager;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

import com.junshan.pub.R;
import com.junshan.pub.databinding.MainTabItemLayoutBinding;
import com.junshan.pub.listener.TitleBarListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab管理
 * Created by 陈俊山 on 2016/8/31.
 */

public class TabManager {
    private FragmentTabHost mTabHost;
    private Context activity;
    private Class fragments[];
    private int images[];
    private int texts[];
    private TitleBarListener mTitleBarListener;
    private FragmentManager fragmentManager;

    public TabManager(Context activity, FragmentTabHost mTabHost, Class fragments[], int images[], int texts[], FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragments = fragments;
        this.images = images;
        this.texts = texts;
        this.mTabHost = mTabHost;//
        this.mTitleBarListener = (TitleBarListener) activity;
        this.fragmentManager = fragmentManager;
    }

    public TabManager(Context activity, TitleBarListener titleBarListener, FragmentTabHost mTabHost, Class fragments[], int texts[], FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragments = fragments;
        this.texts = texts;
        this.mTabHost = mTabHost;//
        this.mTitleBarListener = titleBarListener;
        this.fragmentManager = fragmentManager;
    }

    /**
     * @param fl_content 内容区域的id（例如：R.id.fl_content）
     */
    public void initTab(int fl_content) {
        // 实例化TabHost对象，得到TabHost
        //mTabHost = (FragmentTabHost) activity.findViewById(android.R.id.tabhost);
        mTabHost.setup(activity, fragmentManager, fl_content);
        // 得到fragment的个数
        int count = fragments.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(activity.getResources().getString(texts[i])).setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragments[i], null);
            // 设置Tab按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.main_tab_item_bg);
            mTabHost.getTabWidget().setDividerDrawable(R.color.white);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < texts.length; i++) {
                    if (activity.getResources().getString(texts[i]).equals(s)) {
                        mTitleBarListener.setTitleBarTitle(i);
                        list.get(i).checkBox.setChecked(true);
                        list.get(i).textview.setTextColor(activity.getResources().getColor(R.color.color_f03c3b));
                    } else {
                        list.get(i).checkBox.setChecked(false);
                        list.get(i).textview.setTextColor(activity.getResources().getColor(R.color.light_black));
                    }
                }
            }
        });
    }

    private List<MainTabItemLayoutBinding> list = new ArrayList<>();

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int i) {
        MainTabItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.main_tab_item_layout, null, false);
        list.add(binding);
        if (images == null || images.length == 0) {
            binding.checkBox.setVisibility(View.GONE);
            binding.textview.setPadding(0, 20, 0, 20);
        } else {
            binding.checkBox.setBackgroundResource(images[i]);
        }
        binding.textview.setText(activity.getResources().getString(texts[i]));
        if (i == 0) {
            binding.checkBox.setChecked(true);
            binding.textview.setTextColor(activity.getResources().getColor(R.color.color_f03c3b));
        }
        return binding.getRoot();
    }

    /**
     * 设置当前tab
     *
     * @param position
     */
    public void setCurrentTab(int position) {
        mTabHost.setCurrentTab(position);
    }

    /**
     * 获取当前tab
     *
     * @return
     */
    public int geteCurrentTab() {
        return mTabHost.getCurrentTab();
    }
}
