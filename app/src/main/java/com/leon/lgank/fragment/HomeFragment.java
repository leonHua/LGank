package com.leon.lgank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.lgank.R;
import com.leon.lgank.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class HomeFragment extends Fragment {
    private Context mContext;
    private View mView;
    private ViewPager mViewPager;
    private List<Fragment> mListFragments;
    private String[] mTitles = new String[]{"Android", "IOS", "福利"};
    private TabLayout mTabLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.home_viewpager);
        mTabLayout.addTab(mTabLayout.newTab().setText("Android"));
        mTabLayout.addTab(mTabLayout.newTab().setText("IOS"));
        mTabLayout.addTab(mTabLayout.newTab().setText("福利"));
        initFragments();
        HomeAdapter homeAdapter = new HomeAdapter(getChildFragmentManager(), mContext, mListFragments, mTitles);
        mViewPager.setAdapter(homeAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initFragments() {
        mListFragments = new ArrayList<>();
        mListFragments.add(new HomeAndroidFragment());
        mListFragments.add(new HomeIOSFragment());
        mListFragments.add(new HomeGirlFragment());
    }
}
