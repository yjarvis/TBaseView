package com.jarvis.tbaseview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.design.observer.ObserverMainActivity;
import com.jarvis.tbaseview.design.slg.SLGMainActivity;
import com.jarvis.tbaseview.ui.adapter.AdapterDesignListView;
import com.jarvis.tbaseviewlib.constrans.Constrans;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

/**
 * 设计模式demo界面
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public class _23DesignActivity extends TFragmentActivity {
    private ListView listView;
    private AdapterDesignListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //先执行布局添加，再执行父类中的初始化
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        initView(true);
        setData();
    }


    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        addTitleFragment(new TitleBackFragment().newInstance(titleName, ""));

        listView = (ListView) findViewById(R.id.main_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                onItemClicks(position);
            }
        });
    }

    @Override
    public void setData() {
        adapter = new AdapterDesignListView(context);
        listView.setAdapter(adapter);

    }

    private void onItemClicks(int position) {
        intent = null;
        switch (position) {
            case 0:// 策略模式
                intent = new Intent(context, SLGMainActivity.class);
                break;
            case 1:// 观察者模式
                intent = new Intent(context, ObserverMainActivity.class);
                break;
        }
        if (null != intent) {
            intent.putExtra(Constrans.FLAG_TITLE, adapter.getItem(position));
            startActivity(intent);
        }
    }

    @Override
    public void requestData(boolean isShow) {

    }

    @Override
    public void showFragment(Fragment fragment) {

    }
}
