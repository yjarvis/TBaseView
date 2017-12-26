package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.ui.adapter.AdapterWaterFall;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.TUtils;
import com.jarvis.tbaseviewlib.view.mullayout.TLazyScrollView;
import com.jarvis.tbaseviewlib.view.mullayout.TLazyScrollView.OnScrollListener;
import com.jarvis.tbaseviewlib.view.mullayout.TWaterFallView;
import com.jarvis.tbaseviewlib.view.mullayout.TWaterFallView.OnItemClickListener;

import java.util.ArrayList;

/**
 * 瀑布流
 *
 * @author tansheng
 */
public class _8WaterfallActivity extends TFragmentActivity implements OnClickListener {

    private TitleBackFragment titleBackFragment;
    private TWaterFallView containerLayout;
    private TLazyScrollView scrollView;
    /**
     * 每列显示个数
     */
    private int columnNum = 2;
    private AdapterWaterFall adapter;
    private int[] imgPath = new int[]{R.drawable.meinv1, R.drawable.meinv2, R.drawable.meinv3, R.drawable.meinv4, R.drawable.meinv5, R.drawable.meinv6, R.drawable.meinv7, R.drawable.meinv8, R.drawable.meinv9, R.drawable.meinv1, R.drawable.meinv1, R.drawable.meinv2, R.drawable.meinv3, R.drawable.meinv4, R.drawable.meinv5, R.drawable.meinv6, R.drawable.meinv7, R.drawable.meinv8, R.drawable.meinv9, R.drawable.meinv1};

    private ArrayList<Integer> data = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfall);
        addData();
        initView(true);
    }


    private void addData() {
        for (int i = 0; i < imgPath.length; i++) {
            data.add(imgPath[i]);
        }
    }

    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
        addTitleFragment(titleBackFragment);
        scrollView = (TLazyScrollView) findViewById(R.id.waterfall_scrollview);
        scrollView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onTop() {
                //刷新
                data.clear();
                addData();
                // 滚动到最顶端
                adapter.notifyDataSetInvalidated();
            }

            @Override
            public void onScroll() {

            }

            @Override
            public void onBottom() {
                // 滚动到最底部,加载更多
                addData();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onAutoScroll(int l, int t, int oldl, int oldt) {

            }
        });
        containerLayout = (TWaterFallView) findViewById(R.id.waterfall_container_layout);
        adapter = new AdapterWaterFall(context, data, columnNum);
        containerLayout.setAdapter(adapter);
        containerLayout.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TUtils.showToast(context, "点击ItemClick位置:" + position);
            }
        });
    }

    @Override
    public void onClick(View v) {
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
