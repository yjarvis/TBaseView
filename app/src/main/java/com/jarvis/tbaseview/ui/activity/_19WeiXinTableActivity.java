package com.jarvis.tbaseview.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.customview.TFadeTableView;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

import java.util.ArrayList;

/**
 * 仿微信侧滑时导航栏淡入淡出效果
 * 
 * @author tansheng
 */
public class _19WeiXinTableActivity extends TFragmentActivity implements OnClickListener {

	private TitleBackFragment titleBackFragment;
	private ViewPager viewPager;
	private ArrayList<View> viewList;
	private String[] viewStr = { "微信首页", "通讯录", "发现", "我" };
	private LinearLayout tableLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 先执行布局添加，再执行父类中的初始化
		setContentView(R.layout.activity_weixintable);
		super.onCreate(savedInstanceState);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
		addTitleFragment(titleBackFragment);
		viewPager = (ViewPager) findViewById(R.id.weixintable_viewpager);
		tableLayout = (LinearLayout) findViewById(R.id.table_table_layout);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			/*
			 * position :当前页面，及你点击滑动的页面offset:当前页面偏移的百分比offsetPix:当前页面偏移的像素位置
			 */
			@Override
			public void onPageScrolled(int position, float offset, int offsetPix) {
				int length = tableLayout.getChildCount();
				if (position < length - 1) {
					TFadeTableView leftView = (TFadeTableView) tableLayout.getChildAt(position);
					TFadeTableView rightView = (TFadeTableView) tableLayout.getChildAt(position + 1);
					leftView.setIconAlpha(1 - offset);
					rightView.setIconAlpha(offset);
				}
				System.out.println("1-offset:"+(1-offset)+"   offset:"+offset);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		initViewPager();
	}

	/**
	 * 初始化ViewPager
	 * 
	 * @author tansheng
	 */
	private void initViewPager() {
		if (viewList != null) {
			viewList.clear();
			viewList = null;
		}
		viewList = new ArrayList<View>();

		for (int i = 0; i < 4; i++) {
			TextView textView = new TextView(context);
			textView.setText(viewStr[i]);
			textView.setTextColor(Color.BLACK);
			textView.setTextSize(50);
			viewList.add(textView);
		}

		viewPager.setAdapter(new MyViewPagerAdapter());
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

	private class MyViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return viewList.size();
		}

		/*
		 * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来就是position
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(viewList.get(position));
		}

		/*
		 * 创建一个view
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(viewList.get(position), 0);
			return viewList.get(position);
		}

		/*
		 * 判断出去的view是否等于进来的view 如果为true直接复用
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

}
