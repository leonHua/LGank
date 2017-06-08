package com.leon.lgank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leon.lgank.R;
import com.leon.lgank.model.GankModel;

import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/7
 * 描述:
 */
public class HomeRecyclerviewAdapter extends RecyclerView.Adapter<HomeRecyclerviewAdapter.ViewHolder> {
    private Context mContext;
    private List<GankModel.ResultsEntity> mListData;

    public HomeRecyclerviewAdapter(Context mContext, List<GankModel.ResultsEntity> mListData) {
        this.mContext = mContext;
        this.mListData = mListData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_homefragment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mListData.get(position).getDesc());
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

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_info);
        }
    }
}
