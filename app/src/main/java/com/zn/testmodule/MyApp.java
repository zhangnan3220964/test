package com.zn.testmodule;

import com.zn.basemodule.ModuleApp;

/**
 * Created by zn on 2019-08-16.
 * Describe
 */
public class MyApp extends ModuleApp {
    private static MyApp instance;
    public static MyApp getInstance(){
        if (instance==null){
            instance=new MyApp();
        }
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }
}
