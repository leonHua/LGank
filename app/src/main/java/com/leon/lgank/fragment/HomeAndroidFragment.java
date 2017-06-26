package com.leon.lgank.fragment;

import android.content.Intent;
import android.view.View;

import com.leon.lgank.adapter.HomeRecyclerviewAdapter;
import com.leon.lgank.adapter.HomeRecyclerviewAdapter.OnBaseClickListener;
import com.leon.lgank.common.Constant;
import com.leon.lgank.model.GankModel;
import com.leon.lgank.ui.DetailActivity;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class HomeAndroidFragment extends BaseHomeFragment {
    public View mView;

    @Override
    public String getApiCategory() {
        return "Android";
    }

    @Override
    protected int getItemType() {
        return Constant.ITEM_TYPE_TEXT;
    }

    @Override
    protected void initListener(HomeRecyclerviewAdapter mHomeRecyclerviewAdapter) {
        mHomeRecyclerviewAdapter.addOnClickListener(new OnBaseClickListener() {
            @Override
            public void onClick(int position, GankModel.ResultsEntity entity) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("entity",entity);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position, GankModel.ResultsEntity entity) {
                cloverClick(position, entity);
            }
        });
    }
}
