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
import com.leon.lgank.common.Utils;
import com.leon.lgank.image.ImageManager;
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
    private int mItemType;//条目布局类型
    private OnBaseClickListener mBaseClickListener;

    public interface OnBaseClickListener {
        void onClick(GankModel.ResultsEntity entity);

        void onCoverClick(GankModel.ResultsEntity entity);
    }

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
      final   GankModel.ResultsEntity resultsEntity = mListData.get(position);
        if (mItemType == Constant.ITEM_TYPE_TEXT) {
            holder.tvTitle.setText(resultsEntity.getDesc());
            holder.tvTime.setText(TimeUtils.getFriendlyTimeSpanByNow(Utils.formatDateFromStr(resultsEntity.getPublishedAt())));
            holder.tvAuthor.setText(resultsEntity.getWho());
            if (resultsEntity.getImages() != null && resultsEntity.getImages().size() > 0) {
                ImageManager.getInstance().loadImage(mContext, resultsEntity.getImages().get(0), holder.ivCover);
            } else {
                ImageManager.getInstance().loadImage(mContext, R.drawable.placeholder, holder.ivCover);
            }
            holder.ivCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBaseClickListener.onCoverClick(resultsEntity);
                }
            });
        } else {
            ImageManager.getInstance().loadImage(mContext, resultsEntity.getUrl(), holder.ivGirl);
        }
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseClickListener.onClick(resultsEntity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setmListData(List<GankModel.ResultsEntity> mListData) {
        this.mListData = mListData;
    }


    public void addOnClickListener(OnBaseClickListener baseClickListener) {
        this.mBaseClickListener = baseClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvTime;
        ImageView ivCover;//封面缩率图
        ImageView ivGirl;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            ivGirl = (ImageView) itemView.findViewById(R.id.iv_girl);
        }
    }
}
