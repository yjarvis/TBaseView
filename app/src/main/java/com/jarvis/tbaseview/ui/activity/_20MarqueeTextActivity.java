package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.customview.ScrollTextView;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

public class _20MarqueeTextActivity extends TFragmentActivity {
	private TitleBackFragment titleBackFragment;
	private ScrollTextView switcher1;
	private ScrollTextView switcher2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main2);
		super.onCreate(savedInstanceState);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment=new TitleBackFragment().newInstance(titleName, "");
		addTitleFragment(titleBackFragment);
		switcher1 = (ScrollTextView) findViewById(R.id.switcher1);
		switcher2 = (ScrollTextView) findViewById(R.id.switcher2);

	}

	@Override
	public void setData() {

		String temp = getResources().getString(R.string.info);
		switcher1.setText(temp);
		switcher1.beginScroll();

		switcher2.setText(temp);
		switcher2.beginScroll();

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
