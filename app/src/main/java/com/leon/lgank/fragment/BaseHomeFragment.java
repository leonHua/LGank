package com.leon.lgank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leon.lgank.R;
import com.leon.lgank.adapter.HomeRecyclerviewAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.model.GankModel;
import com.leon.lgank.net.Api;
import com.leon.lgank.net.HttpManager;
import com.leon.lgank.widget.EmptyRecyclerView;
import com.orhanobut.logger.Logger;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：Leon
 * 时间：2017/6/7
 * 描述: HomeFragment中的Android . ios . 福利 fragment 对应的基类封装
 */
public abstract class BaseHomeFragment extends Fragment {
    public Context mContext;
    public View mRootView;
    private EmptyRecyclerView mRecyclerView;
    private HomeRecyclerviewAdapter mHomeRecyclerviewAdapter;
    private Disposable mDisposable;
    private List<GankModel.ResultsEntity> mList = new ArrayList<>();
    private TextView mTvNoNetwork;
    private View mEmptyView;
    private SwipeRefreshLayout mRefreshLayout;
    private AVLoadingIndicatorView mAvi;
    private AVLoadingIndicatorView mAviLoadMore;
    private int mPage = 1;
    private LinearLayout mLayoutLoadMore;




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = getActivity();
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
        initView(view);
        RecyclerView.LayoutManager layoutManager = initLayoutManager();
        mRecyclerView.setLayoutManager(layoutManager);
        mHomeRecyclerviewAdapter = new HomeRecyclerviewAdapter(mContext, mList, getItemType());
        mRecyclerView.setAdapter(mHomeRecyclerviewAdapter);
        mRecyclerView.setmEmptyView(mEmptyView);
        mRecyclerView.hideEmptyView();
        initListener(mHomeRecyclerviewAdapter);

        if (NetworkUtils.isAvailableByPing()) {
            startLoading();
            getDataFromServer(Constant.GET_DATA_TYPE_NOMAL);
        }
    }

    /**
     * 初始化列表的LayoutManager，默认提供LinearLayoutManager，垂直分布
     * 如果希望列表或者自由规格则由子类实现
     */
    protected RecyclerView.LayoutManager initLayoutManager() {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }

    /**
     * 初始化单击事件的监听器，子类选择是否实现
     * @param mHomeRecyclerviewAdapter
     */
    protected void initListener(HomeRecyclerviewAdapter mHomeRecyclerviewAdapter) {

    }
    @Override
    public void onResume() {
        super.onResume();
        Logger.i("fragment_onResume");
        if (!NetworkUtils.isAvailableByPing()) {
            mTvNoNetwork.setVisibility(View.VISIBLE);
            mRefreshLayout.setEnabled(false);
        } else {
            mTvNoNetwork.setVisibility(View.GONE);
            mRefreshLayout.setEnabled(true);
        }
    }

    /**
     * 初始化控件对象，并设置相关监听器和初始化参数
     *
     * @param view
     */
    private void initView(View view) {
        mRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.fragment_recyclerview);
        mEmptyView = view.findViewById(R.id.empty_view);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshlayout);
        mAvi = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        mAviLoadMore = (AVLoadingIndicatorView) view.findViewById(R.id.avi_loadmore);
        mTvNoNetwork = (TextView) view.findViewById(R.id.tv_nonetwork);
        mLayoutLoadMore = (LinearLayout) view.findViewById(R.id.layout_loadmore);
        //设置下拉刷新样式
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mTvNoNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开网络设置界面
                NetworkUtils.openWirelessSettings();
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新事件
                mPage = 1;
                getDataFromServer(Constant.GET_DATA_TYPE_NOMAL);
            }
        });
        //监听上拉加载更多
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollListener());
    }

    /**
     * 从服务端请求数据
     */
    private void getDataFromServer(final int type) {
        Api api = HttpManager.getInstance().getApiService();
        api.getCategoryData(getApiCategory(), Constant.PAGE_SIZE, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(GankModel value) {
                        if (value.getError()) {
                            //服务端请求数据发生错误
                            ToastUtils.showShortSafe("服务端异常，请稍后再试");
                            return;
                        }
                        //更新界面数据
                        if (Constant.GET_DATA_TYPE_NOMAL == type) {
                            //正常模式下，清空之前数据，重新加载
                            Logger.d("eee" + value.getResults().size());
                            mList.clear();
                            mList = value.getResults();
                        } else {
                            //加载更多模式
                            Logger.d("ddd" + value.getResults().size());
                            mList.addAll(value.getResults());
                        }

                        mHomeRecyclerviewAdapter.setmListData(mList);
                        mHomeRecyclerviewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        stopRefreshing();
                        stopLoading();
                        stopLoadingMore();
                    }

                    @Override
                    public void onComplete() {
                        stopRefreshing();
                        stopLoading();
                        stopLoadingMore();
                    }
                });
    }

    /**
     * 获取接口访问分类地址
     *
     * @return String
     */
    protected abstract String getApiCategory();

    /**
     * 不同布局的分类加载，由子类自己实现
     *
     * @return
     */
    protected abstract int getItemType();

    /**
     * 停止下拉刷新
     */
    public void stopRefreshing() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 开启加载中动画
     */
    public void startLoading() {
        mAvi.smoothToShow();
    }

    /**
     * 开启加载更多动画
     */
    public void startLoadingMore() {
        mLayoutLoadMore.setVisibility(View.VISIBLE);
        mAviLoadMore.smoothToShow();
    }

    /**
     * 关闭加载中动画
     */
    public void stopLoading() {
        if (mAvi.isShown()) {
            mAvi.smoothToHide();
        }
    }

    /**
     * 关闭加载更多动画
     */
    public void stopLoadingMore() {
        mLayoutLoadMore.setVisibility(View.GONE);
        mAviLoadMore.smoothToHide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 退出的时候不在回调更新界面
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    /**
     * RecyclerView 滑动监听器
     */
    class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (mList.size() < 1) {
                return;
            }
            //如果正在下拉刷新则放弃监听状态
            if (mRefreshLayout.isRefreshing()) {
                return;
            }
            //当前RecyclerView显示出来的最后一个的item的position,默认为-1
            int lastPosition = -1;
            //当前状态为停止滑动状态SCROLL_STATE_IDLE时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //分别判断三种类型
                if (layoutManager instanceof GridLayoutManager) {
                    //通过LayoutManager找到当前显示的最后的item的position
                    lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof LinearLayoutManager) {
                    lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                    //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                    int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                    ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                    lastPosition = findMax(lastPositions);
                }
                // 判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                //如果相等则说明已经滑动到最后了
                if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                    //此时需要请求等过数据，显示加载更多界面
                    mPage++;
                    startLoadingMore();
                    getDataFromServer(Constant.GET_DATA_TYPE_LOADMORE);
                }
            }
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}