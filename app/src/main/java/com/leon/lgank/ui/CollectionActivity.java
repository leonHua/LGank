package com.leon.lgank.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.View;

import com.leon.lgank.R;
import com.leon.lgank.adapter.HomeRecyclerviewAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.db.DBManager;
import com.leon.lgank.model.GankModel;
import com.leon.lgank.model.SaveModel;
import com.leon.lgank.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends BaseActivity {
    private View mView;
    private EmptyRecyclerView mRecyclerViewCollection;
    private List<GankModel.ResultsEntity> mList = new ArrayList<>();
    private HomeRecyclerviewAdapter mHomeRecyclerviewAdapter;
    private List<SaveModel> mListSave;

    @Override
    protected void initOperation(Intent intent) {
        updateData();
        mRecyclerViewCollection.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mHomeRecyclerviewAdapter = new HomeRecyclerviewAdapter(this, mList, Constant.ITEM_TYPE_TEXT);
        mRecyclerViewCollection.setAdapter(mHomeRecyclerviewAdapter);
        mRecyclerViewCollection.setmEmptyView(findViewById(R.id.empty_view));
        initListener(mHomeRecyclerviewAdapter);
    }

    /**
     * 更新展示数据
     */
    public void updateData() {
        mListSave = DBManager.getAllData();
        if (mListSave != null && mListSave.size() > 0) {
            initAdapterList(mListSave);
        }
    }

    /**
     * 数据对象转换
     *
     * @param listTemp
     */
    private void initAdapterList(List<SaveModel> listTemp) {
        mList.clear();
        for (SaveModel savemodel : listTemp) {
            List<String> images = new ArrayList<String>();
            images.add(savemodel.getImages());
            GankModel.ResultsEntity entity = new GankModel.ResultsEntity(
                    savemodel.get_id()
                    , savemodel.getCreatedAt()
                    , savemodel.getDesc()
                    , savemodel.getPublishedAt()
                    , savemodel.getSource()
                    , savemodel.getType()
                    , savemodel.getUrl()
                    , savemodel.getUsed()
                    , savemodel.getWho()
                    , images);
            mList.add(entity);
        }
    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_collection, null);
        mRecyclerViewCollection = (EmptyRecyclerView) mView.findViewById(R.id.recyclerview_collection);
        return mView;
    }

    @Override
    protected String setToolbarTitle() {
        return "已收藏";
    }

    @Override
    protected void updateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_download).setVisible(false);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_share).setVisible(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
        mHomeRecyclerviewAdapter.setmListData(mList);
        mHomeRecyclerviewAdapter.notifyDataSetChanged();
    }

    protected void initListener(HomeRecyclerviewAdapter mHomeRecyclerviewAdapter) {
        mHomeRecyclerviewAdapter.addOnClickListener(new HomeRecyclerviewAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position, GankModel.ResultsEntity entity) {
                Intent intent = new Intent(CollectionActivity.this, DetailActivity.class);
                String iamges = "";
                if (entity.getImages() != null && entity.getImages().size() > 0) {
                    iamges = entity.getImages().get(0);
                }

                intent.putExtra("entity", entity);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position, GankModel.ResultsEntity entity) {
            }
        });
    }
}
