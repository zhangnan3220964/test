package com.zn.basemodule;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zn.basemodule.fresco.ImagePipelineConfigFactory;

/**
 * Created by zn on 2019-08-16.
 * Describe
 */
public class ModuleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
        Fresco.initialize(this, ImagePipelineConfigFactory.getImagePipelineConfig(this));

    }

    @Override
    protected void attachBaseContext(android.content.Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
