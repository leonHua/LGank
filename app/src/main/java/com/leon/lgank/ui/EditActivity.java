package com.leon.lgank.ui;

import android.content.Intent;
import android.view.View;

import com.leon.lgank.R;

public class EditActivity extends BaseActivity {
    private View mView;

    @Override
    protected void initOperation(Intent intent) {

    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_edit, null);
        return mView;
    }

    @Override
    protected String setToolbarTitle() {
        return "设置";
    }

}
