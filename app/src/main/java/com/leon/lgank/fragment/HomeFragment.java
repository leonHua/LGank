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
    public View mView;
    public ViewPager mViewPager;
    public List<Fragment> mListFragments;
    public String[] mTitles = new String[]{"Android", "IOS", "福利"};

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.home_viewpager);
        tabLayout.addTab(tabLayout.newTab().setText("Android"));
        tabLayout.addTab(tabLayout.newTab().setText("IOS"));
        tabLayout.addTab(tabLayout.newTab().setText("福利"));
        initFragments();
        HomeAdapter homeAdapter = new HomeAdapter(getFragmentManager(), mContext, mListFragments, mTitles);
        mViewPager.setAdapter(homeAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void initFragments() {
        mListFragments = new ArrayList<>();
        mListFragments.add(new HomeAndroidFragment());
        mListFragments.add(new HomeIOSFragment());
        mListFragments.add(new HomeGirlFragment());
    }
}
