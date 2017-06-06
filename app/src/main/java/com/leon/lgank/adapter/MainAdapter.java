package com.leon.lgank.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/6
 * 描述: 主界面ViewPage适配器
 */
public class MainAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mList;

    public MainAdapter(FragmentManager fm, Context mContext, List<Fragment> mList) {
        super(fm);
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }


    public void setmList(List<Fragment> mList) {
        this.mList = mList;
    }
}
