package com.leon.lgank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.leon.lgank.R;
import com.leon.lgank.common.Constant;
import com.leon.lgank.image.ImageManager;
import com.leon.lgank.model.GankModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        GankModel.ResultsEntity resultsEntity = mListData.get(position);
        if (mItemType == Constant.ITEM_TYPE_TEXT) {
            holder.tvTitle.setText(resultsEntity.getDesc());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
            try {
                holder.tvTime.setText(TimeUtils.getFriendlyTimeSpanByNow(simpleDateFormat.parse(resultsEntity.getPublishedAt())));
            } catch (ParseException e) {
                e.printStackTrace();
                holder.tvTime.setText("");
            }
            holder.tvAuthor.setText(resultsEntity.getWho());
            if (resultsEntity.getImages() != null && resultsEntity.getImages().size() > 0) {
                ImageManager.getInstance().loadImage(mContext, resultsEntity.getImages().get(0), holder.ivCover);
            }else{
                ImageManager.getInstance().loadImage(mContext, R.drawable.placeholder, holder.ivCover);
            }
        } else {
            ImageManager.getInstance().loadImage(mContext, resultsEntity.getUrl(), holder.ivGirl);
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
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvTime;
        ImageView ivCover;//封面缩率图
        ImageView ivGirl;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            ivGirl = (ImageView) itemView.findViewById(R.id.iv_girl);
        }
    }
}
