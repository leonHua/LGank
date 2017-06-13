package com.leon.lgank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leon.lgank.R;

import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/13
 * 描述:
 */
public class HistorySearchAdapter extends RecyclerView.Adapter<HistorySearchAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mListData;
    private OnClickListener mOnMyClickListener;

    public interface OnClickListener {
        void onClick(int position);
    }

    public HistorySearchAdapter(Context mContext, List<String> mListData) {
        this.mContext = mContext;
        this.mListData = mListData;
    }

    public void setmOnMyClickListener(OnClickListener mOnMyClickListener) {
        this.mOnMyClickListener = mOnMyClickListener;
    }

    public void setmListData(List<String> mListData) {
        this.mListData = mListData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_history_search, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(mListData.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListData.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnMyClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_info);
            imageView = (ImageView) itemView.findViewById(R.id.iv_clear);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout_historysearch);
        }
    }
}
