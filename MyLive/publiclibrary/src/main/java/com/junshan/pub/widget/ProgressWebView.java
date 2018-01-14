package com.junshan.pub.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.junshan.pub.R;
import com.junshan.pub.utils.LogUtils;


/**
 * Created by benny .
 * on 16:58.2017/3/15 .
 * function：
 */

public class ProgressWebView extends AdvancedWebView {
    private Context context;
    private ProgressBar progressbar;
    private View mErrorView;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                10, 0, 0));

        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(webViewClient);
        setDownloadListener(downloadListener);
    }


    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            CharSequence pnotfound = "404";
            if (title.contains(pnotfound)) {
                view.stopLoading();
                showErrorPage(view);
            }
        }
    }

    /**
     * 文件下载
     */
    public DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            LogUtils.d("url=" + url);
            LogUtils.d("userAgent=" + userAgent);
            LogUtils.d("contentDisposition=" + contentDisposition);
            LogUtils.d("mimetype=" + mimetype);
            LogUtils.d("contentLength=" + contentLength);
            downloadFile(url);

        }
    };

    private void downloadFile(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {//网页加载结束的时候
        }

        /**
         * 页面加载错误时执行的方法，但是在6.0以下，有时候会不执行这个方法
         */
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            showErrorPage(view);//显示错误页面
        }

    };
    boolean mIsErrorPage;

    boolean isShow = true;

    protected void showErrorPage(WebView view) {
        if (!isShow) {
            return;
        }
        isShow = false;
        LinearLayout webParentView = (LinearLayout) view.getParent();
        initErrorPage(view);//初始化自定义页面
        while (webParentView.getChildCount() > 1) {
            webParentView.removeViewAt(0);
        }
        @SuppressWarnings("deprecation")
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewPager.LayoutParams.FILL_PARENT, ViewPager.LayoutParams.FILL_PARENT);
        webParentView.addView(mErrorView, 0, lp);
        mIsErrorPage = true;
    }


    private boolean isload = true;

    /***
     * 显示加载失败时自定义的网页
     */
    protected void initErrorPage(final WebView view) {
        if (mErrorView == null) {
            mErrorView = View.inflate(context, R.layout.error_layout, null);
            RelativeLayout layout = (RelativeLayout) mErrorView.findViewById(R.id.online_error_btn_retry);
            layout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (isload) {
                        isload = false;
                        view.reload();
                    }
                }
            });
            mErrorView.setOnClickListener(null);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}