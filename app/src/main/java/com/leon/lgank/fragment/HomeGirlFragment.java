package com.leon.lgank.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class HomeGirlFragment extends BaseHomeFragment {
    public View mView;


    @Override
    public String getApiCategory() {
        return "Android";
    }

    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
    }
}
