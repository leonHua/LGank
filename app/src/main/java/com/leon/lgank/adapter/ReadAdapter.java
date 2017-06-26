package com.leon.lgank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leon.lgank.R;
import com.leon.lgank.common.Constant;
import com.leon.lgank.common.Utils;
import com.leon.lgank.image.ImageManager;
import com.leon.lgank.model.TianModel;

import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/7
 * 描述:
 */
public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder> {
    private Context mContext;
    private List<TianModel.NewslistEntity> mListData;
    private int mItemType;//条目布局类型
    private OnBaseClickListener mBaseClickListener;

    public interface OnBaseClickListener {
        void onClick(int position, TianModel.NewslistEntity entity);

        void onCoverClick(int position, TianModel.NewslistEntity entity);
    }

    public ReadAdapter(Context mContext, List<TianModel.NewslistEntity> mListData, int mItemType) {
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
        final TianModel.NewslistEntity resultsEntity = mListData.get(position);
        if (mItemType == Constant.ITEM_TYPE_TEXT) {
            holder.tvTitle.setText(resultsEntity.getTitle());
            holder.tvTime.setText(TimeUtils.getFriendlyTimeSpanByNow(Utils.formatDateFromStr(resultsEntity.getCtime())));
            holder.tvAuthor.setText(resultsEntity.getDescription());
            if (resultsEntity.getPicUrl() != null) {
                ImageManager.getInstance().loadImage(mContext, resultsEntity.getPicUrl(), holder.ivCover);
            } else {
                ImageManager.getInstance().loadImage(mContext, R.drawable.notfound, holder.ivCover);
            }
            holder.ivCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (resultsEntity.getPicUrl() != null) {
                        mBaseClickListener.onCoverClick(position, resultsEntity);
                    } else {
                        ToastUtils.showShortSafe("木有发现图片哟");
                    }
                }
            });
        } else {
            ImageManager.getInstance().loadImage(mContext, resultsEntity.getUrl(), holder.ivGirl);
        }
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseClickListener.onClick(position, resultsEntity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setmListData(List<TianModel.NewslistEntity> mListData) {
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
