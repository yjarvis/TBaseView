package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.constants.Constants;
import com.jarvis.tbaseview.ui.adapter.AdapterTHorizontalSlidingView;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.TUtils;
import com.jarvis.tbaseviewlib.view.horizontalsliding.THorizontalSlidingView;
import com.jarvis.tbaseviewlib.view.horizontalsliding.THorizontalSlidingView.OnItemClickListener;

/**
 * 横向无限循环滑动+回弹效果
 * 
 * @author tansheng
 * 
 */
public class _15THorizontalSlidingViewActivity extends TFragmentActivity {
	private TitleBackFragment titleBackFragment;
	private THorizontalSlidingView slidingView;
	private AdapterTHorizontalSlidingView adapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_horizontal_slidingview);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, "");
		addTitleFragment(titleBackFragment);

		slidingView = (THorizontalSlidingView) findViewById(R.id.thorizontal_slidingview);
		slidingView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(int position, View view) {
				TUtils.showToast(context, "点击的位置：" + position);
			}
		});
		// 设置展示数量，也可以在Adapter中的getShowCount()方法中配置
		// slidingView.setShowCount(2);
		// 设置是否可循环滚动
		// slidingView.setIsCirculation(false);
	}

	@Override
	public void setData() {
		adapter = new AdapterTHorizontalSlidingView(context, Constants.imgPath);
		slidingView.setAdapter(adapter);
//		new Handler().postDelayed(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				
//				slidingView.setAdapter(adapter);
//				
//			}
//		}, 500);
	}

	@Override
	public void requestData(boolean isShow) {

	}

	@Override
	public void showFragment(Fragment fragment) {

	}

}
