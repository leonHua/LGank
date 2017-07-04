package com.leon.lgank.ui;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;

import com.leon.lgank.R;

import skin.support.SkinCompatManager;

public class ThemeActivity extends BaseActivity implements View.OnClickListener {
    private View mView;
    private CardView mCardViewGreen;
    private CardView mCardViewBlue;
    private CardView mCardViewYellow;
    private CardView mCardViewPick;
    private CardView mCardViewPurple;
    private CardView mCardViewRed;

    @Override
    protected void initOperation(Intent intent) {
        mCardViewGreen.setOnClickListener(this);
        mCardViewBlue.setOnClickListener(this);
        mCardViewYellow.setOnClickListener(this);
        mCardViewPick.setOnClickListener(this);
        mCardViewPurple.setOnClickListener(this);
        mCardViewRed.setOnClickListener(this);
    }

    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_theme, null);
        mCardViewGreen = (CardView) mView.findViewById(R.id.card_green);
        mCardViewBlue = (CardView) mView.findViewById(R.id.card_blue);
        mCardViewYellow = (CardView) mView.findViewById(R.id.card_yellow);
        mCardViewPick = (CardView) mView.findViewById(R.id.card_pick);
        mCardViewPurple = (CardView) mView.findViewById(R.id.card_purple);
        mCardViewRed = (CardView) mView.findViewById(R.id.card_red);
        return mView;
    }

    @Override
    protected String setToolbarTitle() {
        return "切换主题";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_green:
                SkinCompatManager.getInstance().restoreDefaultTheme();
                break;
            case R.id.card_blue:
                SkinCompatManager.getInstance().loadSkin("skinblue.skin", null);
                break;
            case R.id.card_yellow:
                SkinCompatManager.getInstance().loadSkin("skinyellow.skin", null);
                break;
            case R.id.card_pick:
                SkinCompatManager.getInstance().loadSkin("skinpick.skin", null);
                break;
            case R.id.card_purple:
                SkinCompatManager.getInstance().loadSkin("skinpurple.skin", null);
                break;
            case R.id.card_red:
                SkinCompatManager.getInstance().loadSkin("skinred.skin", null);
                break;
        }
    }
}
