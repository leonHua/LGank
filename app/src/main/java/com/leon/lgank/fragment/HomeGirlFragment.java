package com.leon.lgank.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leon.lgank.adapter.HomeRecyclerviewAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.model.GankModel;
import com.leon.lgank.ui.PicActivity;

import java.util.ArrayList;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class HomeGirlFragment extends BaseHomeFragment {
    public View mView;
    private ArrayList<String> mListPicUrls = null;

    @Override
    public String getApiCategory() {
        return "福利";
    }

    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
    }

    @Override
    protected int getItemType() {
        return Constant.ITEM_TYPE_IMAGE;
    }

    @Override
    protected void initListener(HomeRecyclerviewAdapter mHomeRecyclerviewAdapter) {
        mHomeRecyclerviewAdapter.addOnClickListener(new HomeRecyclerviewAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position ,GankModel.ResultsEntity entity) {
                Intent intent = new Intent(mContext, PicActivity.class);
                mListPicUrls = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    mListPicUrls.add(mList.get(i).getUrl());
                }
                intent.putStringArrayListExtra("piclist", mListPicUrls);
                intent.putExtra("position",position);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position ,GankModel.ResultsEntity entity) {

            }
        });
    }
}
