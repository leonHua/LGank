package com.leon.lgank.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

import com.leon.lgank.R;
import com.leon.lgank.fragment.OtherCategroyFragent;

public class OtherCategoryActivity extends BaseActivity {
    private View mView;
    private Fragment mFragment;

    @Override
    protected void initOperation(Intent intent) {
        String categroy = intent.getStringExtra("categroy");
        mFragment = OtherCategroyFragent.newInstance(categroy);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contain, mFragment);
        transaction.commit();
    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_other_category, null);
        return mView;
    }

    @Override
    protected void updateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_download).setVisible(false);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_share).setVisible(false);
    }

}
