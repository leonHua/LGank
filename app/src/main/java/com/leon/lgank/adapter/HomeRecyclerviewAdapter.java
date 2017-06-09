package com.leon.lgank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leon.lgank.R;
import com.leon.lgank.common.Constant;
import com.leon.lgank.image.ImageManager;
import com.leon.lgank.model.GankModel;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/7
 * 描述:
 */
public class HomeRecyclerviewAdapter extends RecyclerView.Adapter<HomeRecyclerviewAdapter.ViewHolder> {
    private Context mContext;
    private List<GankModel.ResultsEntity> mListData;
    private int mItemType;//条目布局类型

    public HomeRecyclerviewAdapter(Context mContext, List<GankModel.ResultsEntity> mListData, int mItemType) {
        this.mContext = mContext;
        this.mListData = mListData;
        this.mItemType = mItemType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (mItemType == Constant.ITEM_TYPE_TEXT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_homefragment, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_homefragment_girl, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mItemType == Constant.ITEM_TYPE_TEXT) {
            holder.textView.setText(mListData.get(position).getDesc());
        } else {
            Logger.i("URL---" + mListData.get(position).getUrl());
            ImageManager.getInstance().loadImage(mContext, mListData.get(position).getUrl(), holder.ivGirl);
        }

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setmListData(List<GankModel.ResultsEntity> mListData) {
        this.mListData = mListData;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView ivGirl;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_info);
            ivGirl = (ImageView) itemView.findViewById(R.id.iv_girl);
        }
    }
}
