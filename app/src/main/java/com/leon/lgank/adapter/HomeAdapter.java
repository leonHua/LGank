package com.leon.lgank.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/6
 * 描述: HomeFragment中的ViewPage适配器
 */
public class HomeAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mList;
    private String[] mTitles;

    public HomeAdapter(FragmentManager fm, Context mContext, List<Fragment> mList, String[] mTitles) {
        super(fm);
        this.mList = mList;
        this.mContext = mContext;
        this.mTitles = mTitles;
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

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
