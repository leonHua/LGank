package com.leon.lgank.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.leon.lgank.R;
import com.leon.lgank.adapter.PicAdapter;

import java.util.List;

public class PicActivity extends BaseActivity {
    private View mView;
    private ViewPager mViewPager;

    @Override
    protected void initOperation(Intent intent) {
        List<String> list = intent.getStringArrayListExtra("piclist");
        int currentPosition = intent.getIntExtra("position", 0);
       // updateToolBar();
        PicAdapter picAdapter = new PicAdapter(this, list);
        mViewPager.setAdapter(picAdapter);
        mViewPager.setCurrentItem(currentPosition);
    }

    /**
     * 更新重新定制Toolbar
     */
    private void updateToolBar() {
        mToolbar.setBackgroundColor(getResources().getColor(R.color.black));
    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_pic, null);
        mViewPager = (ViewPager) mView.findViewById(R.id.pic_viewpager);
        return mView;
    }

    @Override
    protected String setToolbarTitle() {
        return "";
    }
}
