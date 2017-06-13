package com.leon.lgank.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.leon.lgank.R;
import com.leon.lgank.adapter.HistorySearchAdapter;
import com.leon.lgank.common.IPreference;
import com.leon.lgank.common.PreferenceImpl;
import com.leon.lgank.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private FlexboxLayout mFlexboxLayout;
    private PreferenceImpl mPreUtils;
    private EmptyRecyclerView mRecyclerViewHistory;
    private final String HISTORY_SEARCH = "history_search";
    private HistorySearchAdapter mHistorySearchAdapter;
    private List<String> mHotTitles = new ArrayList<String>();
    private List<String> mHistoryTitles = new ArrayList<String>();
    private ImageView mIvDeleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mFlexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
        mRecyclerViewHistory = (EmptyRecyclerView) findViewById(R.id.recyclerview_history);
        mIvDeleteAll = (ImageView) findViewById(R.id.iv_deleteall);
        mPreUtils = (PreferenceImpl) IPreference.prefHolder.getPreference(this, HISTORY_SEARCH);

        if (mPreUtils.getAll(HISTORY_SEARCH) != null) {
            mHistoryTitles = mPreUtils.getAll(HISTORY_SEARCH);
        }
        mHistorySearchAdapter = new HistorySearchAdapter(this, mHistoryTitles);
        mRecyclerViewHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        mRecyclerViewHistory.setAdapter(mHistorySearchAdapter);
        mRecyclerViewHistory.setmEmptyView(new TextView(this));
        mHistorySearchAdapter.setmOnMyClickListener(new HistorySearchAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                ToastUtils.showShort("position " + position);
            }
        });
        mIvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreUtils.remove(HISTORY_SEARCH);
                mHistoryTitles.clear();
                mHistorySearchAdapter.setmListData(mPreUtils.getAll(HISTORY_SEARCH));
                mHistorySearchAdapter.notifyDataSetChanged();
            }
        });
        initTag();
    }

    /**
     * 初始化流标签信息
     */
    private void initTag() {
        mHotTitles.add("RxJava");
        mHotTitles.add("RxAndroid");
        mHotTitles.add("数据库");
        mHotTitles.add("自定义控件");
        mHotTitles.add("下拉刷新");
        mHotTitles.add("mvp");
        mHotTitles.add("直播");
        mHotTitles.add("权限管理");
        mHotTitles.add("Retrofit");
        mHotTitles.add("OkHttp");
        mHotTitles.add("WebWiew");
        mHotTitles.add("热修复");
        // 通过代码向FlexboxLayout添加View
        for (int i = 0; i < mHotTitles.size(); i++) {
            TextView textView = new TextView(this);
            textView.setBackground(getResources().getDrawable(R.drawable.flexbox_text_bg));
            textView.setText(mHotTitles.get(i));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(30, 30, 30, 30);
            textView.setClickable(true);
            textView.setTextColor(getResources().getColor(R.color.secondary_text));
            mFlexboxLayout.addView(textView);
            //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            if (params instanceof FlexboxLayout.LayoutParams) {
                FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
                //layoutParams.setFlexBasisPercent(0.5f);
                layoutParams.setMargins(10, 10, 20, 10);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tv = (TextView) v;
                    ToastUtils.showShort(tv.getText());
                    mHistoryTitles.add(tv.getText().toString().trim());
                    mPreUtils.putAll(HISTORY_SEARCH, mHistoryTitles);

                    mHistorySearchAdapter.setmListData(mPreUtils.getAll(HISTORY_SEARCH));
                    mHistorySearchAdapter.notifyDataSetChanged();
                }
            });
        }

    }
}
