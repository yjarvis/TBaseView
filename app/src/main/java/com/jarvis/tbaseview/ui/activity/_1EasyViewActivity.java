package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.customview.EasyView;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

/**
 * 简单的自定义控件界面
 * @author tansheng
 *
 */
public class _1EasyViewActivity extends TFragmentActivity implements OnClickListener {

	private TitleBackFragment titleBackFragment;
	private EasyView customView;// 自定义组件
	private Button start;// 开始按钮
	private Button stop;// 停止按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easy);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment=new TitleBackFragment().newInstance(titleName, null);
		addTitleFragment(titleBackFragment);
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);

		// 得到自定义的控件
		customView = (EasyView) findViewById(R.id.custom_view);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start:// 开始动画
			customView.startPlay();
			break;
		case R.id.stop:// 停止动画
			customView.stopPlay();
			break;
		}
	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub

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
