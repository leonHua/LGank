package com.leon.lgank.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.leon.lgank.adapter.HomeRecyclerviewAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.model.GankModel;
import com.leon.lgank.ui.DetailActivity;

/**
 * Created by Administrator on 2017/6/11.
 */

public class OtherCategroyFragent extends BaseHomeFragment {
    private static final String CATEGROY = "categroy";

    @Override
    protected String getApiCategory() {
       String categroy =  getArguments().getString("categroy");
        return categroy;
    }

    @Override
    protected int getItemType() {
        return Constant.ITEM_TYPE_TEXT;
    }

    @Override
    protected void initListener(HomeRecyclerviewAdapter mHomeRecyclerviewAdapter) {
        mHomeRecyclerviewAdapter.addOnClickListener(new HomeRecyclerviewAdapter.OnBaseClickListener() {
            @Override
            public void onClick(GankModel.ResultsEntity entity) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("url", entity.getUrl());
                startActivity(intent);
            }

            @Override
            public void onCoverClick(GankModel.ResultsEntity entity) {
                ToastUtils.showShortSafe("点击头像");
            }
        });
    }
    public static Fragment newInstance(String arg){
        OtherCategroyFragent fragment = new OtherCategroyFragent();
        Bundle bundle = new Bundle();
        bundle.putString( CATEGROY, arg);
        fragment.setArguments(bundle);
        return fragment;
    }
}
