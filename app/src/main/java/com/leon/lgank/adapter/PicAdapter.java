package com.leon.lgank.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leon.lgank.R;
import com.leon.lgank.image.ImageManager;
import com.leon.lgank.image.PinchImageView;

import java.util.List;

/**
 * 作者：Leon
 * 时间：2017/6/12
 * 描述:
 */
public class PicAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mListUrl;

    public PicAdapter(Context mContext, List<String> mListUrl) {
        this.mContext = mContext;
        this.mListUrl = mListUrl;
    }

    @Override
    public int getCount() {
        return mListUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //ImageView imageView = new ImageView(mContext);
        PinchImageView imageView = new PinchImageView(mContext);
        imageView.setImageResource(R.drawable.header);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (mListUrl == null || mListUrl.size() < 1) {
            ImageManager.getInstance().loadImage(mContext, R.drawable.placeholder, imageView);
        } else {
            ImageManager.getInstance().loadImage(mContext, mListUrl.get(position), imageView);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setmListUrl(List<String> mListUrl) {
        this.mListUrl = mListUrl;
    }
}
