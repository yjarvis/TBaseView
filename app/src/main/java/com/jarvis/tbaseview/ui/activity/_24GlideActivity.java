package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.ui.adapter.AdapterGlide;
import com.jarvis.tbaseviewlib.bitmap.GlideBitmap.GlideHelp;
//import com.jarvis.tbaseviewlib.http.OkHttp.HttpHelpOk;
//import com.jarvis.tbaseviewlib.http.OkHttp.ParamsOkHttp;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

/**
 * Glide图片库测试
 * @author tansheng 2016/11/03
 */
public class _24GlideActivity extends TFragmentActivity {

	private TitleBackFragment titleBackFragment;
	private ListView listView;
	private TextView textView;
	private AdapterGlide adapter;
//	private HttpHelpOk httpHelpOk;
	
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_fresco);
		initView(true);
//		httpHelpOk=HttpHelpOk.getInstance(context);
//		ParamsOkHttp params=new ParamsOkHttp();
//		params.addParamBody("username","test2");
//		params.addParamBody("password","12346");
//		httpHelpOk.doPost("http://115.159.107.98/Login.php",params);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment=new TitleBackFragment().newInstance(titleName, "");
		addTitleFragment(titleBackFragment);

		listView=(ListView) findViewById(R.id.fresco_listview);
		textView= (TextView) findViewById(R.id.fresco_size);
		textView.setText(new GlideHelp(context).getCacheSize(null));
	}

	@Override
	public void setData() {
		adapter=new AdapterGlide(context);
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
