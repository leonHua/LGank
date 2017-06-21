package com.leon.lgank.ui;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leon.lgank.R;

public class DetailActivity extends BaseActivity {
    private View mView;
    private WebView mWebView;
    private String mUrl;
    private boolean mIsSave = false;


    @Override
    protected void initOperation(Intent intent) {
        startLoading();
        mUrl = intent.getStringExtra("url");
        mWebView = (WebView) mView.findViewById(R.id.webview);
        if (StringUtils.isEmpty(mUrl)) {
            ToastUtils.showShort("地址加载失败，请稍后再试");
            return;
        }
        initWebView();
        //加载网页
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_detail, null);
        return mView;
    }


    @Override
    protected String setToolbarTitle() {
        return "详情查看";
    }

    /**
     * webview相关参数设置
     */
    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        //支持js脚本
        webSettings.setJavaScriptEnabled(true);
        //支持缩放
        webSettings.setSupportZoom(true);
        //支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //多窗口
        webSettings.supportMultipleWindows();
        //当webview调用requestFocus时为webview设置节点
        webSettings.setNeedInitialFocus(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //优先使用缓存:
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //提高渲染的优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 开启H5(APPCache)缓存功能
        webSettings.setAppCacheEnabled(true);
        // 开启 DOM storage 功能
        webSettings.setDomStorageEnabled(true);
        // 应用可以有数据库
        webSettings.setDatabaseEnabled(true);
        // 可以读取文件缓存(manifest生效)
        webSettings.setAllowFileAccess(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //网页加载完成
                    stopLoading();
                }
            }
        });
    }

    /**
     * 处理返回按键，浏览器回退，不是直接关闭
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                startShareIntent("text/plain", "分享一篇实用文章：" + mUrl);
                break;
            case R.id.action_save:
                mIsSave = mIsSave ? false : true;
                if (mIsSave) {
                    ToastUtils.showShortSafe("收藏成功");
                    item.setIcon(R.mipmap.menu_action_save_choosen);
                } else {
                    ToastUtils.showShortSafe("取消收藏");
                    item.setIcon(R.mipmap.menu_action_save);
                }

                break;
        }
        return true;
    }

    @Override
    protected void updateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_download).setVisible(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清除缓存
        mWebView.clearCache(true);
        //清除历史记录
        mWebView.clearHistory();
        mWebView.destroy();
    }

}
