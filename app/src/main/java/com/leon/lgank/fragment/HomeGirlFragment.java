package com.leon.lgank.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.leon.lgank.R;
import com.leon.lgank.adapter.HomeRecyclerviewAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.model.GankModel;
import com.leon.lgank.ui.PicActivity;

import java.util.ArrayList;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class HomeGirlFragment extends BaseHomeFragment implements View.OnClickListener {
    private FloatingActionButton mItemLinearlayout;
    private FloatingActionButton mItemGridlayout;
    private FloatingActionButton mItemStaggeredlayout;
    private FloatingActionMenu mActionMenu;
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
    protected void initOperation(View mRootView) {
        mItemLinearlayout = (FloatingActionButton) mRootView.findViewById(R.id.menu_item_linearlayout);
        mItemGridlayout = (FloatingActionButton) mRootView.findViewById(R.id.menu_item_gridlayout);
        mItemStaggeredlayout = (FloatingActionButton) mRootView.findViewById(R.id.menu_item_staggeredlayout);
        mActionMenu = (FloatingActionMenu) mRootView.findViewById(R.id.actionmenu);
        mActionMenu.setVisibility(View.VISIBLE);
        mItemLinearlayout.setOnClickListener(this);
        mItemGridlayout.setOnClickListener(this);
        mItemStaggeredlayout.setOnClickListener(this);
    }


    /**
     * 滑动结束，显示actionbutton
     */
    @Override
    protected void stopScrolling() {
        mActionMenu.showMenu(true);
    }

    /**
     * 在滑动中，隐藏掉actionbutton
     */
    @Override
    protected void startScrolling() {
        mActionMenu.hideMenu(true);
    }

    @Override
    protected void initListener(HomeRecyclerviewAdapter mHomeRecyclerviewAdapter) {
        mHomeRecyclerviewAdapter.addOnClickListener(new HomeRecyclerviewAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position, GankModel.ResultsEntity entity) {
                Intent intent = new Intent(mContext, PicActivity.class);
                mListPicUrls = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    mListPicUrls.add(mList.get(i).getUrl());
                }
                intent.putStringArrayListExtra("piclist", mListPicUrls);
                intent.putExtra("position", position);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position, GankModel.ResultsEntity entity) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_item_linearlayout:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                mHomeRecyclerviewAdapter.setmIsStaggered(false);
                mActionMenu.close(true);
                break;
            case R.id.menu_item_gridlayout:
                mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
                mHomeRecyclerviewAdapter.setmIsStaggered(false);
                mActionMenu.close(true);
                break;
            case R.id.menu_item_staggeredlayout:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                mHomeRecyclerviewAdapter.setmIsStaggered(true);
                mActionMenu.close(true);
                break;
        }
    }
}
