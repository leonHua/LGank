package com.leon.lgank;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import skin.support.SkinCompatManager;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * 作者：Leon
 * 时间：2017/6/8
 * 描述:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        //换肤框架初始化
        SkinCompatManager.init(this).addInflater(new SkinMaterialViewInflater()).loadSkin();
        Utils.init(this);
    }
}
