package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.customview.PaintView;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

/**
 * Paint的部分用法
 * 
 * @author tansheng
 * 
 */
public class _4PaintViewActivity extends TFragmentActivity implements OnClickListener {

	private TitleBackFragment titleBackFragment;
	private PaintView customView;// 自定义组件
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paint);
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, null);
		addTitleFragment(titleBackFragment);

		btn0 = (Button) findViewById(R.id.paintView_btn0);
		btn1 = (Button) findViewById(R.id.paintView_btn1);
		btn2 = (Button) findViewById(R.id.paintView_btn2);
		btn3 = (Button) findViewById(R.id.paintView_btn3);
		btn4 = (Button) findViewById(R.id.paintView_btn4);
		btn5 = (Button) findViewById(R.id.paintView_btn5);

		// 得到自定义的控件
		customView = (PaintView) findViewById(R.id.custom_view);
		// 设置图片资源
		customView.setBitmap(R.drawable.meinv4);

		btn0.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.paintView_btn0:// 线性渲染
			customView.setType(0);
			break;
		case R.id.paintView_btn1:// 环形渲染
			customView.setType(1);
			break;
		case R.id.paintView_btn2:// 扫面渲染
			customView.setType(2);
			break;
		case R.id.paintView_btn3:// 位图渲染
			customView.setType(3);
			break;
		case R.id.paintView_btn4:// 组合渲染
			customView.setType(4);
			break;
		case R.id.paintView_btn5:// 倒影效果
			customView.setType(5);
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
