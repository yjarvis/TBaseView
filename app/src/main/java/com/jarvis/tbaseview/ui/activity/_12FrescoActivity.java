package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.support.fresco.config.ConfigConstants;
import com.jarvis.tbaseview.ui.adapter.AdapterFresco;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

/**
 * Fresco图片库测试
 * @author tansheng
 */
public class _12FrescoActivity extends TFragmentActivity {

	private TitleBackFragment titleBackFragment;
	private ListView listView;
	private AdapterFresco adapter;
	
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_fresco);
		//初始化Fresco
		Fresco.initialize(context, ConfigConstants.getImagePipelineConfig(this));
		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment=new TitleBackFragment().newInstance(titleName, "");
		addTitleFragment(titleBackFragment);
		
		listView=(ListView) findViewById(R.id.fresco_listview);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
			}
		});

	}

	@Override
	public void setData() {
		adapter=new AdapterFresco(context);
		listView.setAdapter(adapter);

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
