package com.leon.lgank.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingCallback;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.leon.lgank.R;
import com.leon.lgank.common.Constant;
import com.leon.lgank.ui.CollectionActivity;
import com.leon.lgank.ui.DetailActivity;
import com.leon.lgank.ui.MainActivity;
import com.leon.lib.settingview.LSettingItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：Leon
 * 时间：2017/6/6
 */
public class MeFragment extends Fragment {
    public View mView;
    public MainActivity mActivity;
    private LSettingItem mSettingItemBlog;
    private LSettingItem mSettingItemFollow;
    private LSettingItem mSettingItemSave;
    private LSettingItem mSettingItemVersion;
    private String mName;
    private String mBlog;
    private String mOther;
    private TextView mTvNickName;
    private CircleImageView mIvHeader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_me, container, false);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mSettingItemBlog = (LSettingItem) view.findViewById(R.id.item_blog);
        mSettingItemFollow = (LSettingItem) view.findViewById(R.id.item_follow);
        mSettingItemSave = (LSettingItem) view.findViewById(R.id.item_save);
        mSettingItemVersion = (LSettingItem) view.findViewById(R.id.item_version);
        mTvNickName = (TextView) view.findViewById(R.id.tv_nickname);
        mIvHeader = (CircleImageView) view.findViewById(R.id.iv_header);
        initData();
        mActivity.showEdit();
        mSettingItemBlog.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Intent intent = new Intent(mActivity, DetailActivity.class);
                intent.putExtra("url", mBlog);
                intent.putExtra("isfromme", true);
                startActivity(intent);
            }
        });

        mSettingItemVersion.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                ToastUtils.showShortSafe("已经是最新版本啦~");
            }
        });
        mSettingItemFollow.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Intent intent = new Intent(mActivity, DetailActivity.class);
                intent.putExtra("url", mOther);
                intent.putExtra("isfromme", true);
                startActivity(intent);
            }
        });
        mSettingItemSave.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivity(new Intent(mActivity, CollectionActivity.class));
            }
        });
        mIvHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoxingMediaLoader.getInstance().init(new MyBoxingMediaLoader()); // 需要实现IBoxingMediaLoader
                BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
                config.needGif() // 支持gif，相机，设置最大选图数
                        .withMediaPlaceHolderRes(R.drawable.placeholder); // 设置默认图片占位图，默认无
                // 启动预览原图界面，依赖boxing-impl.
                Boxing.of(config).withIntent(mActivity, BoxingActivity.class).start(MeFragment.this, 10000);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mTvNickName.setText(mName);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mName = SPUtils.getInstance().getString("nickname");
        mBlog = SPUtils.getInstance().getString("blogurl");
        mOther = SPUtils.getInstance().getString("otherurl");
        String headimg = SPUtils.getInstance().getString("headimg");
        if (!StringUtils.isEmpty(headimg)) {
            mIvHeader.setImageBitmap(BitmapFactory.decodeFile(headimg));
        }
        if (StringUtils.isEmpty(mName)) {
            mName = Constant.NICKNAME;
        }
        if (StringUtils.isEmpty(mBlog)) {
            mBlog = Constant.BLOGURL;
        }
        if (StringUtils.isEmpty(mOther)) {
            mOther = Constant.OTHERURL;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<BaseMedia> medias = Boxing.getResult(data);
        if (medias != null && medias.size() > 0) {
            String path = "file://" + medias.get(0).getPath();
            mIvHeader.setImageBitmap(BitmapFactory.decodeFile(medias.get(0).getPath()));
            SPUtils.getInstance().put("headimg", medias.get(0).getPath());
            //Glide.with(mIvHeader.getContext())
            //        .load(path)
            //        .error(R.drawable.loaderror)
            //        .crossFade().centerCrop()
            //        .into(mIvHeader);
        }
    }

    class MyBoxingMediaLoader implements IBoxingMediaLoader {

        @Override
        public void displayThumbnail(@NonNull ImageView img, @NonNull String absPath, int width, int height) {
            String path = "file://" + absPath;
            try {
                Glide.with(img.getContext()).load(path).placeholder(R.drawable.placeholder).crossFade().centerCrop().override(width, height).into(img);
            } catch (IllegalArgumentException ignore) {
            }
        }

        @Override
        public void displayRaw(@NonNull final ImageView img, @NonNull String absPath, int width, int height, final IBoxingCallback callback) {
            String path = "file://" + absPath;
            BitmapTypeRequest<String> request = Glide.with(img.getContext())
                    .load(path)
                    .asBitmap();
            if (width > 0 && height > 0) {
                request.override(width, height);
            }
            request.listener(new RequestListener<String, Bitmap>() {
                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    if (callback != null) {
                        callback.onFail(e);
                        return true;
                    }
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    if (resource != null && callback != null) {
                        img.setImageBitmap(resource);
                        callback.onSuccess();
                        return true;
                    }
                    return false;
                }
            }).into(img);

        }
    }
}


