package com.leon.lgank.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leon.lgank.R;
import com.leon.lgank.common.Constant;
import com.leon.lgank.ui.CollectionActivity;
import com.leon.lgank.ui.DetailActivity;
import com.leon.lgank.ui.MainActivity;
import com.leon.lib.settingview.LSettingItem;

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

}
