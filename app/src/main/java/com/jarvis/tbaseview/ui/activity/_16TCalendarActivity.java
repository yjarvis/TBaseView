package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;
import com.jarvis.tbaseviewlib.utils.TUtils;
import com.jarvis.tbaseviewlib.view.calendar.TCalendarView;
import com.jarvis.tbaseviewlib.view.calendar.TCalendarView.OnTCalenderChangeListener;
import com.jarvis.tbaseviewlib.view.calendar.TCalendarView.TCalenderItemsOnClick;
/**
 * 自定义日历
 * @author tansheng
 *
 */
public class _16TCalendarActivity extends TFragmentActivity {
	private TitleBackFragment titleBackFragment;
	private TCalendarView calendarView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_calendar);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment=new TitleBackFragment().newInstance(titleName, "");
		addTitleFragment(titleBackFragment);
		calendarView=(TCalendarView) findViewById(R.id.calendar_view);
		calendarView.setOnItemsClickListener(new TCalenderItemsOnClick() {
			
			@Override
			public void itemsOnClick(View v, int position, int year, int month, int day) {
				TUtils.showToast(context, "位置："+position+"    "+year+"年"+month+"月"+day+"日");
			}
		});
		calendarView.setOnTCalenderChangeListener(new OnTCalenderChangeListener() {
			
			@Override
			public void onPageSelected(int year, int month, int arg0) {
				TUtils.showToast(context, year+ "年" + month + "月");
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		TUtils.showToast(context, calendarView.getYear()+ "年" + calendarView.getMonth() + "月");
		//跳转到某年某月
//		calendarView.setCurrentItem(2018, 10);
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
