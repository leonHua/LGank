package com.leon.lgank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.lgank.R;
import com.leon.lgank.adapter.HomeRecyclerviewAdapter;
import com.leon.lgank.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/7
 * 描述:
 */
public class BaseHomeFragment extends Fragment {
    private Context mContext;
    public View mRootView;
    private EmptyRecyclerView mRecyclerView;
    private HomeRecyclerviewAdapter mHomeRecyclerviewAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home_android_ios, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.fragment_recyclerview);
        View emptyView = View.inflate(mContext, R.layout.emptyview, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        List<String> list = new ArrayList<>();
        int sum = 20;
        for (int i = 0; i < sum; i++) {
            list.add("test" + i);
        }
        mHomeRecyclerviewAdapter = new HomeRecyclerviewAdapter(mContext, list);
        mRecyclerView.setAdapter(mHomeRecyclerviewAdapter);
        mRecyclerView.setmEmptyView(emptyView);

    }
}

