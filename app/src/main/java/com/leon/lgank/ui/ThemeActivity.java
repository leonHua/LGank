package com.leon.lgank.ui;

import android.content.Intent;
import android.view.View;

import com.leon.lgank.R;

public class ThemeActivity extends BaseActivity implements View.OnClickListener {
    private View mView;

    @Override
    protected void initOperation(Intent intent) {
// 指定皮肤插件
//        SkinCompatManager.getInstance().loadSkin("blueskin.skin", null);
    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_theme, null);
        return mView;
    }

    @Override
    protected String setToolbarTitle() {
        return "切换主题";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_green:
                break;
            case R.id.card_blue:
                break;
            case R.id.card_yellow:
                break;
            case R.id.card_pick:
                break;
            case R.id.card_purple:
                break;
            case R.id.card_red:
                break;


        }
    }
}
