package com.komori.sophix;

import android.app.Application;

import com.taobao.sophix.SophixManager;

/**
 * Created by 朱成 on 2018/11/12.
 */
public class MyRealApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
