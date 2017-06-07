package com.leon.lgank.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leon.lgank.R;


/**
 * 作者：Leon
 * 时间：2017/6/7
 * 描述: 图片处理类
 */
public class ImageManager {
    private static ImageManager mImageManager = null;

    private ImageManager() {
    }

    public static ImageManager getInstance() {
        if (mImageManager == null) {
            mImageManager = new ImageManager();
        }
        return mImageManager;
    }

    /**
     * 加载图片
     *
     * @param context
     * @param path
     * @param targetImageView 使用默认的占位等待图片和错误图片
     */
    public void loadImage(Context context, Object path, ImageView targetImageView) {
        Glide.with(context).load(path).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(targetImageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param path
     * @param targetImageView
     * @param placeHolderResourceId 加载中占位图片
     * @param errorResourceId       加载错误占位图片
     */
    public void loadImage(Context context, Object path, ImageView targetImageView, int placeHolderResourceId, int errorResourceId) {
        Glide.with(context).load(path).placeholder(placeHolderResourceId).error(errorResourceId).into(targetImageView);
    }

    public void loadRoundImage(){

    }
}
