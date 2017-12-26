package com.jarvis.tbaseview.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.jarvis.tbaseview.R;
import com.jarvis.tbaseview.ui.adapter.AdapterDeleteListView2;
import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

/**
 * 横向滑动删除
 * @author tansheng
 *
 */
public class _9DeleteListViewActivity2 extends TFragmentActivity {

	private TitleBackFragment titleBackFragment;
	private ListView listView;
	private AdapterDeleteListView2 adapter;


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_delete_listview);

		initView(true);
		setData();
	}


	@Override
	public void initView(boolean isStatusBar) {
		super.initView(isStatusBar);
		titleBackFragment = new TitleBackFragment().newInstance(titleName, "");
		addTitleFragment(titleBackFragment);

		listView = (ListView) findViewById(R.id.delete_listview);
		// listView.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1, int position,
		// long arg3) {
		// Utils.showToast(context, "点击第"+position+"处的Item");
		//
		// }
		// });

	}

	@Override
	public void setData() {
		adapter = new AdapterDeleteListView2(context);
		listView.setAdapter(adapter);
//		adapter.setOnItemClickListener(new AdapterDeleteListView.OnItemClickCallBack() {
//
//			@Override
//			public void OnItemClickListener(int position) {
//				TUtils.showToast(context, "点击第" + position + "处的Item");
//			}
//		});

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
