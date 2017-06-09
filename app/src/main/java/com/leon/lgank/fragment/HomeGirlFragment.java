package com.leon.lgank.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leon.lgank.common.Constant;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class HomeGirlFragment extends BaseHomeFragment {
    public View mView;


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
}
