package com.leon.lgank.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.leon.lgank.adapter.ReadAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.model.TianModel;
import com.leon.lgank.net.HttpManager;
import com.leon.lgank.ui.PicActivity;
import com.leon.lgank.ui.ReadDetailActivity;
import com.leon.lgank.widget.EmptyRecyclerView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.blankj.utilcode.util.NetworkUtils.isConnected;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class ReadFragment extends Fragment {
    public Context mContext;
    public View mView;
    private EmptyRecyclerView mEmptyRecyclerView;
    private ReadAdapter mReadAdapter;
    private List<TianModel.NewslistEntity> mList = new ArrayList<>();
    private TextView mTvNoNetwork;
    private View mEmptyView;
    private SwipeRefreshLayout mRefreshLayout;
    private AVLoadingIndicatorView mAvi;
    private AVLoadingIndicatorView mAviLoadMore;
    private int mPage = 1;
    private LinearLayout mLayoutLoadMore;
    private Disposable mDisposable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_android_ios, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mEmptyRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mReadAdapter = new ReadAdapter(mContext, mList, Constant.ITEM_TYPE_TEXT);
        mEmptyRecyclerView.setAdapter(mReadAdapter);
        mEmptyRecyclerView.setmEmptyView(view.findViewById(R.id.empty_view));
        initListener();
        if (isConnected()) {
            startLoading();
            getDataFromServer(Constant.GET_DATA_TYPE_NOMAL);
        }
    }

    private void initListener() {
        //监听点击事件
        mReadAdapter.addOnClickListener(new ReadAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position, TianModel.NewslistEntity entity) {
                Intent intent = new Intent(mContext, ReadDetailActivity.class);
                intent.putExtra("entity", entity);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position, TianModel.NewslistEntity entity) {
                Intent intent = new Intent(mContext, PicActivity.class);
                ArrayList<String> listPicUrls = new ArrayList<String>();
                listPicUrls.add(entity.getPicUrl());
                intent.putStringArrayListExtra("piclist", listPicUrls);
                startActivity(intent);
            }
        });

    }

    /**
     * 初始化控件对象，并设置相关监听器和初始化参数
     *
     * @param view
     */
    private void initView(View view) {
        mEmptyRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.fragment_recyclerview);
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
        mEmptyRecyclerView.addOnScrollListener(new RecyclerViewScrollListener());
    }

    public void getDataFromServer(final int type) {
        HttpManager.getInstance()
                .getApiService(Constant.BASE_URL_READ)
                .getReadData(Constant.APIKEY, Constant.PAGE_SIZE, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TianModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(TianModel value) {
                        if (value.getCode() != 200) {
                            //服务端请求数据发生错误
                            ToastUtils.showShortSafe("服务端异常，请稍后再试");
                            return;
                        }
                        //更新界面数据
                        if (Constant.GET_DATA_TYPE_NOMAL == type) {
                            //正常模式下，清空之前数据，重新加载
                            mList.clear();
                            mList = value.getNewslist();
                        } else {
                            //加载更多模式
                            mList.addAll(value.getNewslist());
                        }

                        mReadAdapter.setmListData(mList);
                        mReadAdapter.notifyDataSetChanged();
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
     * 停止下拉刷新
     */
    public void stopRefreshing() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
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
