package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

/**
 * 淘宝商品详情滑动
 * @author tansheng
 * 
 */
public class _17TaobaoActivity extends TFragmentActivity implements OnClickListener {

	private TitleBackFragment titleBackFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taobao);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
		addTitleFragment(titleBackFragment);
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
