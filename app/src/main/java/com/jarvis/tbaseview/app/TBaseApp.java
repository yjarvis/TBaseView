package com.jarvis.tbaseview.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by tansheng on 2017/8/8.
 */

public class TBaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        NsdServerUtil.getInstance(this).registerService().discoverService();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

}
