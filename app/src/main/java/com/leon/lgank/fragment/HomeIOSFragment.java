package com.leon.lgank.fragment;

import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.leon.lgank.adapter.HomeRecyclerviewAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.model.GankModel;
import com.leon.lgank.ui.DetailActivity;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class HomeIOSFragment extends BaseHomeFragment {
    public View mView;

    @Override
    public String getApiCategory() {
        return "iOS";
    }

    @Override
    protected int getItemType() {
        return Constant.ITEM_TYPE_TEXT;
    }
    @Override
    protected void initListener(HomeRecyclerviewAdapter mHomeRecyclerviewAdapter) {
        mHomeRecyclerviewAdapter.addOnClickListener(new HomeRecyclerviewAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position ,GankModel.ResultsEntity entity) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("url", entity.getUrl());
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position ,GankModel.ResultsEntity entity) {
                ToastUtils.showShortSafe("点击头像");
            }
        });
    }
}
