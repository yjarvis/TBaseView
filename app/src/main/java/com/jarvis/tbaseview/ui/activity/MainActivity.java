package com.jarvis.tbaseview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.ui.adapter.AdapterMainListView;
import com.jarvis.tbaseviewlib.constrans.Constrans;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleHomeFragment;
import com.jarvis.tbaseviewlib.utils.TUtils;

/**
 * 主页
 *
 * @author tansheng
 */
public class MainActivity extends TFragmentActivity {

    private ListView listView;
    private AdapterMainListView adapter;
    private TitleHomeFragment titleHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //先执行布局添加，再执行父类中的初始化
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
//		Bmob.initialize(this, CacheData.bUpdataKey);
//		BmobUpdateAgent.update(this);
        // AppUnionSDK.getInstance(context).initSdk();
        // AppUnionSDK.getInstance(context).showAppList();

        // 开启有米插屏
//		SpotManager.getInstance(context).showSpotAds(this);
        initView(true);
        setData();
    }

    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        titleHomeFragment = new TitleHomeFragment().newInstance("首页", "");
        addTitleFragment(titleHomeFragment);

        listView = (ListView) findViewById(R.id.main_listview);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                onItemClicks(position);
            }
        });

    }

    @Override
    public void setData() {
        adapter = new AdapterMainListView(context);
        listView.setAdapter(adapter);

    }

    private void onItemClicks(int position) {
        intent = null;
        switch (position) {
            case 0:// 简单的自定义控件界面
                intent = new Intent(context, _1EasyViewActivity.class);
                break;
            case 1:// 贝塞尔曲线控件界面
                intent = new Intent(context, _2TrackActivity.class);
                break;
            case 2:// 图片美颜
                intent = new Intent(context, _3BitmapViewActivity.class);
                break;
            case 3:// 图片渲染（Paint渲染的用法）
                intent = new Intent(context, _4PaintViewActivity.class);
                break;
            case 4:// 旋转木马3D+倒影效果
                // intent = new Intent(context, _5Rotate3DActivity.class);
                TUtils.showToast(context, "下一版本即将更新此功能，敬请期待");
                break;
            case 5:// 标签云效果
                intent = new Intent(context, _6CloudActivity.class);
                break;
            case 6:// 高斯模糊效果
                intent = new Intent(context, _7BlurActivity.class);
                break;
            case 7:// 瀑布流
                intent = new Intent(context, _8WaterfallActivity.class);
                break;
            case 8:// ListView滑动删除
                intent = new Intent(context, _9DeleteListViewActivity2.class);
                break;
            case 9:// GistView滑动删除
                TUtils.showToast(context, "下一版本即将更新此功能，敬请期待");
                break;
            case 10:// 仿QQ空间说说选择图片
                intent = new Intent(context, _11QQPictureActivity1.class);
                break;
            case 11:// Fresco图片加载库
                intent = new Intent(context, _12FrescoActivity.class);
                break;
            case 12:// RecyclerView控件
                break;
            case 13:// 断点下载
                break;
            case 14:// 横向无限循环滑动+回弹效果
                intent = new Intent(context, _15THorizontalSlidingViewActivity.class);
                break;
            case 15:// 自定义日历控件
                intent = new Intent(context, _16TCalendarActivity.class);
                break;
            case 16:// 仿淘宝商品详情滚动翻页
                intent = new Intent(context, _17TaobaoActivity.class);
                break;
            case 17:// 插件式开发
                intent = new Intent(context, _18PluginActivity.class);
                break;
            case 18:// 仿微信导航栏淡入淡出
                intent = new Intent(context, _19WeiXinTableActivity.class);
                break;
            case 19:// 上下左右的跑马灯效果
                intent = new Intent(context, _20MarqueeTextActivity.class);
                break;
            case 20:// 拖动添加效果
                intent = new Intent(context, _21DragActivity.class);
                break;
            case 21:// 人脸识别
                intent = new Intent(context, _22FaceActivity.class);
                break;
            case 22:// 21种设计模式
                intent = new Intent(context, _23DesignActivity.class);
                break;
            case 23:// Glide加载库
                intent = new Intent(context, _24GlideActivity.class);
                break;
            case 24:// Sensor传感器
                intent = new Intent(context, _25SensorActivity.class);
                break;
            case 25:// 监听短信
                intent = new Intent(context, _26SimActivity.class);
                break;
            case 26:// 模拟定位
                intent = new Intent(context, _27LocationActivity.class);
            case 27:// Jni使用
                intent = new Intent(context, _28JniActivity.class);
                break;
            case 28:// 贴纸功能
                intent = new Intent(context,_29TagsActivity.class);
                break;
        }
        if (null != intent) {
            intent.putExtra(Constrans.FLAG_TITLE, adapter.getItem(position));
            startActivity(intent);
        }
    }

    @Override
    public void requestData(boolean isShow) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showFragment(Fragment fragment) {
        // TODO Auto-generated method stub

    }


}
