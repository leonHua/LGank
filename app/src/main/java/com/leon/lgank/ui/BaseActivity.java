package com.leon.lgank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.leon.lgank.R;
import com.wang.avi.AVLoadingIndicatorView;

import skin.support.app.SkinCompatActivity;

/**
 * 作者：Leon
 * 时间：2017/6/9
 * 描述:
 */
public abstract class BaseActivity extends SkinCompatActivity{
    protected Toolbar mToolbar;
    private LinearLayout mRootLayout;
    private AVLoadingIndicatorView mLoading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(stopSwipe());
        Intent intent = getIntent();
        initToolbar();
        mRootLayout = (LinearLayout) findViewById(R.id.root_layout);
        mLoading = (AVLoadingIndicatorView) findViewById(R.id.avi_loading);
        //添加显示界面，由子类实现
        mRootLayout.addView(addContentView());
        //界面加载完成，调取子类具体业务
        initOperation(intent);
    }

    /**
     * 具体的业务逻辑，由子类实现
     */
    protected abstract void initOperation(Intent intent);

    /**
     * 加载子类布局
     *
     * @return
     */
    protected abstract View addContentView();

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * 初始化Toolbar信息
     */
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(setToolbarTitle());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base_toolbar, menu);
        updateOptionsMenu(menu);
        return true;
    }

    /**
     * 子类可以根据需要动态更新菜单
     *
     * @param menu
     */
    protected void updateOptionsMenu(Menu menu) {
    }

    /**
     * 设置Toolbar主标题
     *
     * @return
     */
    protected String setToolbarTitle() {
        return "LGank";
    }

    /**
     * 开启加载中动画
     */
    public void startLoading() {
        mLoading.smoothToShow();
    }

    /**
     * 关闭加载中动画
     */
    public void stopLoading() {
        if (mLoading.isShown()) {
            mLoading.smoothToHide();
        }
    }

    /**
     * 打开分享界面
     *
     * @param type
     */
    public void startShareIntent(String type, String content) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);
        share_intent.setType(type);
        share_intent.putExtra(Intent.EXTRA_TEXT, content);
        share_intent = Intent.createChooser(share_intent, "分享");
        startActivity(share_intent);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    protected boolean stopSwipe() {
        return true;
    }
}
