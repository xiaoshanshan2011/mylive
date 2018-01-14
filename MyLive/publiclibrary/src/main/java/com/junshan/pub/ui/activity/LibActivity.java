package com.junshan.pub.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.junshan.pub.R;
import com.junshan.pub.adapter.CommonCycAdapter;
import com.junshan.pub.bean.MessageEvent;
import com.junshan.pub.bean.NodataOrNetBean;
import com.junshan.pub.config.BaseMsgConstant;
import com.junshan.pub.databinding.EmptyLayoutBinding;
import com.junshan.pub.databinding.TitletarLayoutBinding;
import com.junshan.pub.databinding.XrecyclerviewLayoutBinding;
import com.junshan.pub.listener.BindListener;
import com.junshan.pub.manager.StatusBar;
import com.junshan.pub.utils.AppUtils;
import com.junshan.pub.utils.FileUtils;
import com.junshan.pub.utils.LogUtils;
import com.junshan.pub.utils.PhotoUtils;
import com.junshan.pub.widget.DividerItemDecoration;
import com.junshan.pub.widget.SlidingLayout;
import com.junshan.pub.widget.SpDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.junshan.pub.utils.PhotoUtils.imageUri;

/**
 * Created by 陈俊山 on 4/7/2559.
 */
public abstract class LibActivity<T extends ViewDataBinding, D> extends FragmentActivity implements BindListener, View.OnClickListener, XRecyclerView.LoadingListener {
    protected final String TAG = this.getClass().getName();
    private LayoutInflater inflater;
    private LinearLayout.LayoutParams params;
    private LinearLayout.LayoutParams params_match;
    protected T mBinding;//绑定布局文件并执行常用方法
    protected XrecyclerviewLayoutBinding lvBinding;//当子类是列表的时候这个才可用
    protected TitletarLayoutBinding titleBinding;//头部布局
    protected boolean isSlidingClose = true;//是否测滑关闭activity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横竖屏切换
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        StatusBar.showStatusBar(this, R.color.transparent);
        inflater = LayoutInflater.from(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params_match = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //实例化一个线性布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //Title部分
        titleBinding = DataBindingUtil.inflate(inflater, R.layout.titletar_layout, null, false);
        linearLayout.addView(titleBinding.getRoot(), params);
        intiStatusBar();
        //Content部分
        if (bindLayout() != 0) {
            mBinding = DataBindingUtil.inflate(inflater, bindLayout(), null, false);
            linearLayout.addView(mBinding.getRoot(), params_match);
        } else if (bindItemLayout() != 0) {
            lvBinding = DataBindingUtil.inflate(inflater, R.layout.xrecyclerview_layout, null, false);
            initXrecyclerview();
            linearLayout.addView(lvBinding.getRoot(), params_match);
        }
        setContentView(linearLayout);
        initOnCreate(savedInstanceState);
        initTitleBar();
        initData();
        initEvent();
        //注册事件
        EventBus.getDefault().register(this);
        if (isSlidingClose) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
        SpDialog.clear();
    }

    protected void initEvent() {
    }

    protected void initData() {
    }

    private EmptyLayoutBinding emptyBinding = null;

    private void initXrecyclerview() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lvBinding.xrecyclerview.setLayoutManager(layoutManager);
        lvBinding.xrecyclerview.setLoadingListener(this);
        emptyBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.empty_layout, null, false);
        lvBinding.xrecyclerview.setEmptyView(emptyBinding.getRoot());
    }

    /**
     * 初始化状态栏
     */
    private void intiStatusBar() {
        if (titleBinding != null && AppUtils.getSystemVersion() < 19) {
            titleBinding.statusBar.setVisibility(View.GONE);
        } else {
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) titleBinding.statusBar.getLayoutParams();
            linearParams.height = AppUtils.getStatusBarHeight();
            titleBinding.statusBar.setLayoutParams(linearParams);
        }
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int bindItemLayout() {
        return 0;
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    /**
     * 获取ListView中的每一个Item
     *
     * @param binding
     * @param item
     */
    protected void getListVewItem(T binding, D item, int position) {
    }

    /**
     * 获取ListViewd Item的每个点击事件
     *
     * @param bean
     * @param position
     */
    protected void onItemClick(D bean, int position) {
    }

    protected CommonCycAdapter<T, D> adapter = null;

    public List<D> datas = new ArrayList<>();

    public void addData(List<D> datas) {
        if (datas != null && datas.size() > 0) {
            this.datas.addAll(datas);
            setData(this.datas);
        }
    }

    public void clearData() {
        datas = new ArrayList<>();
    }

    public void setData(List<D> datas) {
        if (lvBinding == null) {
            return;
        }
        if (datas == null && lvBinding != null) {
            datas = new ArrayList<>();
            isEmpty(true);
        } else if (datas.size() == 0) {
            isEmpty(true);
        } else {
            isEmpty(false);
        }

        if (adapter == null) {
            adapter = new CommonCycAdapter<T, D>(this, bindItemLayout(), datas) {
                @Override
                protected void getItem(T binding, D bean, int position) {
                    getListVewItem(binding, bean, position);
                }

                @Override
                protected void itemOnclick(D bean, int position) {
                    onItemClick(bean, position);
                }
            };
            lvBinding.xrecyclerview.setAdapter(adapter);
        } else {
            adapter.updata(datas);
        }
    }

    public void setTitle(String text) {
        titleBinding.tvTitle.setText(text);
    }

    @Override
    public void initTitleBar() {
        titleBinding.btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLeftClick();
            }
        });
        titleBinding.btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRightClick();
            }
        });
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivity(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void startFragment(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, CommonActivity.class);
        intent.putExtra(CommonActivity.FRAGMENT_CLASS, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startFragment(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, CommonActivity.class);
        intent.putExtra(CommonActivity.FRAGMENT_CLASS, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void closeLoad() {
        if (lvBinding.xrecyclerview != null) {
            lvBinding.xrecyclerview.refreshComplete();
            lvBinding.xrecyclerview.loadMoreComplete();
        }
    }

    /**
     * 显示无数据布局
     */
    public void isEmpty(boolean isempty) {
        if (lvBinding != null) {
            if (isempty) {
                lvBinding.llEmpty.setVisibility(View.VISIBLE);
                lvBinding.tvTitle.setText(getString(R.string.str_no_data_title));
                lvBinding.tvContent.setText(R.string.str_no_data_content);
                lvBinding.ivType.setImageResource(R.mipmap.ic_huixinyun_no_data);
                lvBinding.btnLoad.setVisibility(View.GONE);
            } else {
                lvBinding.llEmpty.setVisibility(View.GONE);
            }
        }
    }

    /**
     * @param i 列数
     */
    protected void setRecycViewGrid(int i) {
        if (lvBinding == null)
            return;
        lvBinding.xrecyclerview.setLayoutManager(new GridLayoutManager(this, i));
    }


    /**
     * 设置RecycleView分割线
     */
    public void setDivider() {
        if (lvBinding != null) {
            lvBinding.xrecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        }
    }

    /**
     * 设置RecycleView分割线
     */
    public void setDivider(int divider) {
        if (lvBinding != null) {
            lvBinding.xrecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, divider));
        }
    }

    /**
     * 设置没有数据或者没网络的数据
     *
     * @param bean
     */
    public void setNodataOrNonet(NodataOrNetBean bean) {
        if (lvBinding != null) {
            lvBinding.llEmpty.setVisibility(View.VISIBLE);
            lvBinding.tvTitle.setText(bean.getTitle());
            lvBinding.tvContent.setText(bean.getContent());
            lvBinding.ivType.setImageResource(bean.getImg());
            if (bean.getType() == NodataOrNetBean.NODATA) {
                lvBinding.btnLoad.setVisibility(View.GONE);
            } else if (bean.getType() == NodataOrNetBean.NONET) {
                lvBinding.btnLoad.setVisibility(View.VISIBLE);
                lvBinding.btnLoad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onRefresh();
                    }
                });
            }
        }
    }

    /**
     * 设置没有没网络的数据
     */
    public void setNonet() {
        if (lvBinding != null) {
            lvBinding.llEmpty.setVisibility(View.VISIBLE);
            lvBinding.llEmpty.setOnClickListener(this);
            lvBinding.tvTitle.setText(getString(R.string.str_no_net_title));
            lvBinding.tvContent.setText(R.string.str_no_net_content);
            lvBinding.ivType.setImageResource(R.mipmap.ic_huixinyun_no_net);
            lvBinding.btnLoad.setVisibility(View.VISIBLE);
            lvBinding.btnLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRefresh();
                }
            });
        }
    }

    /**
     * 设置没有没网络的数据
     */
    public void setNoData() {
        if (lvBinding != null) {
            lvBinding.llEmpty.setVisibility(View.VISIBLE);
            lvBinding.tvTitle.setText(getString(R.string.str_no_data_title));
            lvBinding.tvContent.setText(R.string.str_no_data_content);
            lvBinding.ivType.setImageResource(R.mipmap.ic_huixinyun_no_data);
            lvBinding.btnLoad.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消事件注册
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(MessageEvent msgEvent) {
        if (msgEvent.getType() == BaseMsgConstant.NONET) {
            setNonet();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PhotoUtils.CHOOSE_PHOTO_REQUEST:
                try {
                    Uri imageUri = data.getData();
                    photoResult(PhotoUtils.getImagePath(this, imageUri));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PhotoUtils.TAKE_PHOTO_REQUEST:
                if (resultCode == RESULT_CANCELED) {
                    PhotoUtils.delteImageUri(this, imageUri);
                } else {
                    photoResult(PhotoUtils.getImagePath(this, imageUri));
                }
                break;
            case PhotoUtils.File_REQUEST:
                //选择文件回调
                try {
                    Uri imageUri = data.getData();
                    photoResult(imageUri.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 99:
                try {
                    Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
                    photoResult(FileUtils.getPath(this, uri));
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 选择相片回调
     */
    public void photoResult(String path) {
        LogUtils.d(path);
    }
}
