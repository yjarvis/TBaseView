package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jarvis.tbaseview.jni.JniHelloWorld;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.utils.LogUtils;

/**
 * Jni调用
 * Created by tansheng on 2017/9/14.
 */

public class _28JniActivity extends TFragmentActivity {
    static {
        //加载so库
        System.loadLibrary("ndk_tbaseview_test");
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        String str=JniHelloWorld.setSay("Hi! TBase");
        int number= JniHelloWorld.square(5);
        LogUtils.e("Jni输出："+str);
        LogUtils.e("Jni输出："+number);
    }

    @Override
    public void setData() {

    }

    @Override
    public void requestData(boolean isShow) {

    }

    @Override
    public void showFragment(Fragment fragment) {

    }
}
